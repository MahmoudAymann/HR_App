package com.gsgroup.hrapp.ui.fragment.map.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentShareLocationBinding
import com.gsgroup.hrapp.util.PermissionUtil
import com.gsgroup.hrapp.util.PermissionUtil.goToSettingsPermissions
import com.gsgroup.hrapp.util.PermissionUtil.requestAppPermissions
import com.gsgroup.hrapp.util.observe

class ShareLocationFragment : DialogFragment(), ActivityResultCallback<ActivityResult> {

    lateinit var binding: FragmentShareLocationBinding
    val mViewModel: ShareLocationViewModel by viewModels()

    lateinit var register: ActivityResultLauncher<Intent>
    val args : ShareLocationFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MY_DIALOG_FRAGMENT)
        register = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShareLocationBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        mViewModel.apply {
            gotData(args)
            observe(mutableLiveData) {
                when (it) {
                    Codes.BACK_BUTTON_PRESSED->dismiss()
                    Codes.ALLOW_PERMISSION -> activity?.requestAppPermissions(PermissionUtil.getPhoneCriticalPermissions()) {
                        mViewModel.permissionResult(it)
                    }
                    Codes.OPEN_PERMISSION_SETTING -> activity?.goToSettingsPermissions(
                        getString(R.string.please_allow_permission_phone_critical),
                        register
                    )
                }
            }
            observe(resultLiveData) {

            }
        }
        return binding.root
    }


    override fun onActivityResult(result: ActivityResult?) {
        if (PermissionUtil.hasAllPhoneCriticalPermissions(requireContext())) {
            mViewModel.isGranted.set(true)
        } else
            mViewModel.isGranted.set(false)
    }
}