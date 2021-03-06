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
import com.gsgroup.hrapp.ui.activity.details.DetailsActivity
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.PermissionUtil.goToSettingsPermissions
import com.gsgroup.hrapp.util.PermissionUtil.requestAppPermissions

class ShareLocationFragment : DialogFragment(){

    lateinit var binding: FragmentShareLocationBinding
    val mViewModel: ShareLocationViewModel by viewModels()

    lateinit var register: ActivityResultLauncher<Intent>
    val args : ShareLocationFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MY_DIALOG_FRAGMENT)
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
                    Codes.BACK_BUTTON_PRESSED -> dismiss()
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        dismiss()
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }

        }
        return binding.root
    }

    private fun showProgress(b: Boolean = true) {
        castToActivity<DetailsActivity> {
            it?.baseShowProgress?.set(b)
        }
    }
}