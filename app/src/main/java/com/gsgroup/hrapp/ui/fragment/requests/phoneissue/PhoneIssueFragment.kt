package com.gsgroup.hrapp.ui.fragment.requests.phoneissue

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentPhoneIssueBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog
import com.gsgroup.hrapp.util.showSuccessfulDialog

class PhoneIssueFragment : BaseFragment<FragmentPhoneIssueBinding, PhoneIssueViewModel>() {
    override fun pageTitle(): String = getString(R.string.phone_issue_request)
    override val mViewModel: PhoneIssueViewModel by viewModels()

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