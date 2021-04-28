package com.gsgroup.hrapp.ui.fragment.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.databinding.FragmentMapBinding
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.AppUtil.goToSettingsPermissions
import com.gsgroup.hrapp.util.MapUtil.isGPSEnabled
import com.gsgroup.hrapp.util.MapUtil.openGPSDialog
import com.gsgroup.hrapp.util.PermissionUtil.AppPermissionResult.*
import com.gsgroup.hrapp.util.PermissionUtil.requestAppPermissions
import com.gsgroup.hrapp.util.Status.*
import timber.log.Timber

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback,
    ActivityResultCallback<ActivityResult> {

    private lateinit var register: ActivityResultLauncher<Intent>
    private val args: MapFragmentArgs by navArgs()
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var locationCallback: LocationCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMap()
        handleFragRes()
        mViewModel.apply {
            gotArgs(args)
            observe(mutableLiveData) {
                when (it) {
                    Codes.OPEN_CITY_LIST -> navigateSafe(
                        MapFragmentDirections.actionMapFragmentToBottomSheetFragment(
                            citiesList
                        )
                    )
                    Codes.OPEN_AREA_LIST -> if (isCitySelected) navigateSafe(
                        MapFragmentDirections.actionMapFragmentToBottomSheetFragment(
                            areasList
                        )
                    ) else {
                        activity?.showErrorDialog(getString(R.string.please_select_city_first))
                    }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    SUCCESS -> {
                        showProgress(false)
                    }
                    MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    LOADING -> showProgress()
                }
            }
        }
    }


    private fun handleFragRes() {
        listenForResult<SearchItemInterface>(ConstString.RESULT_FROM_BOTTOMSHEET_LIST) {
            mViewModel.gotResultFromBottomSheet(it)
        }
    }

    override fun onResume() {
        super.onResume()
        askForLocationPermissions()
    }

    private fun startGetLocation() {
        //this fun will not be called unless i have the permissions first
        if (requireActivity().isGPSEnabled()) {
            retrieveLocationUpdates()
        } else {
            requireActivity().openGPSDialog {
                retrieveLocationUpdates()
            }
        }
    }

    private fun retrieveLocationUpdates() {
        locationCallback = MapUtil.requestLocationUpdates(requireActivity(), fusedLocationClient, true) {
            mViewModel.gotCurrentLocation(it)
        }
    }

    override fun onPause() {
        stopLocationUpdates()
        super.onPause()
    }

    override fun onStop() {
        stopLocationUpdates()
        super.onStop()
    }

    private fun stopLocationUpdates() {
        locationCallback?.let {
            fusedLocationClient?.removeLocationUpdates(it)
        }
    }


    private fun initMap() {
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun askForLocationPermissions() {
        if (PermissionUtil.hasAllLocationPermissions(requireContext())) {
            startGetLocation()

        } else {
            requireActivity().requestAppPermissions(PermissionUtil.getAllLocationPermissions()) {
                when (it) {
                    ALL_GOOD -> {
                        startGetLocation()
                    }
                    OPEN_SETTING -> requireActivity().goToSettingsPermissions(
                        getString(R.string.allow_location_permission),
                        register
                    )
                    FAIL -> {
                        askForLocationPermissions()
                    }
                }
            }
        }
    }

    override fun onMapReady(gm: GoogleMap?) {
        mViewModel.onMapReady(gm)
    }

    override val mViewModel: MapViewModel by viewModels()
    override fun pageTitle(): String = ""

    override fun onActivityResult(result: ActivityResult?) {
        askForLocationPermissions()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        data?.let {
//            val states = LocationSettingsStates.fromIntent(it)
            if (requestCode == Codes.REQUEST_CHECK_SETTINGS) {
                if (resultCode == Activity.RESULT_OK) {
                    startGetLocation()
                }
            }
        } ?: Timber.e("data is null")
    }

}


/**
 1- enter -> askForLocationPermissions


 */