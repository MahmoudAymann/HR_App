package com.gsgroup.hrapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.HomeIds
import com.gsgroup.hrapp.data.model.HomeIds.*
import com.gsgroup.hrapp.databinding.FragmentHomeBinding
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.PermissionUtil.AppPermissionResult.ALL_GOOD
import com.gsgroup.hrapp.util.PermissionUtil.AppPermissionResult.OPEN_SETTING
import com.gsgroup.hrapp.util.PermissionUtil.goToSettingsPermissions
import com.gsgroup.hrapp.util.PermissionUtil.requestAppPermissions
import kotlin.properties.Delegates

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    ActivityResultCallback<ActivityResult> {
    private var isCheckIn by Delegates.notNull<Boolean>()
    private lateinit var register: ActivityResultLauncher<Intent>
    override fun pageTitle(): String = getString(R.string.home)

    override val mViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    is HomeIds -> openScreens(it)
                    Codes.CHECK_IN -> navigateToMap(true)
                    Codes.CHECK_OUT -> navigateToMap(false)
                    Codes.MISSED_DOCUMENT-> activity?.showWarningDialog(getString(R.string.missed_document))
                }
            }
        }
    }

    private fun navigateToMap(isCheckIn: Boolean) {
        this.isCheckIn = isCheckIn
        showMapScreen()
    }

    private fun showMapScreen() {
        showDetailsActivity(R.id.mapFragment, getIntentForMap(isCheckIn))
    }

    private fun getIntentForMap(isCheckIn: Boolean): Intent {
        return Intent().putExtra(ConstString.BUNDLE_FRAGMENT,
            Bundle().apply { putBoolean("isCheckIn", isCheckIn) })
    }

    private fun openScreens(it: HomeIds) {
        when (it) {
            DIRECT_MANAGER -> showDetailsActivity(R.id.directManagerFragment)
            REQUESTS -> showDetailsActivity(R.id.requestsCornerFragment)
            SIGN_IN_OUT_LOGS -> showDetailsActivity(R.id.signInOutLogFragment)
            HR -> showDetailsActivity(R.id.HRFragment)
            MY_TEAM -> showDetailsActivity(R.id.myTeamFragment)
            COMPANY_POLICES -> showDetailsActivity(R.id.companyPoliciesFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.refreshData()
    }

    override fun onActivityResult(result: ActivityResult?) {
        navigateToMap(isCheckIn)
    }

}

