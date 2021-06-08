package com.gsgroup.hrapp.ui.fragment.requests.myrequest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentMyRequestsBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class MyRequestsFragment : BaseFragment<FragmentMyRequestsBinding, MyRequestsViewModel>() {
    override fun pageTitle(): String = getString(R.string.my_requests)

    override val mViewModel: MyRequestsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.MY_REQUESTS_SCREEN -> navigateSafe(
                        MyRequestsFragmentDirections.actionMyRequestsFragmentToMineRequestsFragment(
                            true
                        )
                    )
                    Codes.REQUESTS_SENT_TO_ME_SCREEN -> navigateSafe(
                        MyRequestsFragmentDirections.actionMyRequestsFragmentToMineRequestsFragment(
                            false
                        )
                    )
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