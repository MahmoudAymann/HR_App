package com.gsgroup.hrapp.ui.fragment.requests.exp_certificate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentExpCertificateBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog
import com.gsgroup.hrapp.util.showSuccessfulDialog

class ExpCertificateFragment :
    BaseFragment<FragmentExpCertificateBinding, ExpCertificateViewModel>() {
    override fun pageTitle(): String = getString(R.string.exp_certificate_request)
    override val mViewModel: ExpCertificateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message){
                            closeFragment()
                        }
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