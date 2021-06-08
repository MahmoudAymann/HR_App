package com.gsgroup.hrapp.ui.fragment.requests.myrequest.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentRequestDetailsBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class RequestDetailsFragment :
    BaseFragment<FragmentRequestDetailsBinding, RequestDetailsViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: RequestDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {

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