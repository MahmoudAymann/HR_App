package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentMedicalApprovalDetailsBinding
import com.gsgroup.hrapp.util.AppUtil.openMailIntent
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class MedicalApprovalDetailsFragment :
    BaseFragment<FragmentMedicalApprovalDetailsBinding, MedicalApprovalDetailsViewModel>() {

    val args: MedicalApprovalDetailsFragmentArgs by navArgs()

    override val mViewModel: MedicalApprovalDetailsViewModel by viewModels()

    override fun pageTitle(): String = args.title ?: ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            gotData(args)
            observe(mutableLiveData) {
                when (it) {
                    Codes.CHRONICAL_MEDICAL -> { requireActivity().openMailIntent(obsMail.get()) }
                    Codes.PHYSICAL_MEDICAL, Codes.SURGENT_MEDICAL, Codes.XRAY_MEDICAL, Codes.ANALYSIS_MEDICAL -> {
                        requireActivity().openMailIntent(obsMail.get())
                    }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }
        }
    }
}