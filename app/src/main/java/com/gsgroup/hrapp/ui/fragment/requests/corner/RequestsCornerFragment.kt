package com.gsgroup.hrapp.ui.fragment.requests.corner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentRequestsCornerBinding
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showWarningDialog

class RequestsCornerFragment :
    BaseFragment<FragmentRequestsCornerBinding, RequestsCornerViewModel>() {
    override fun pageTitle(): String = getString(R.string.requests)
    override val mViewModel: RequestsCornerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.AUDIT_SCREEN->activity?.showWarningDialog(getString(R.string.still_working_on_it))
                    Codes.HR_REQUESTS_SCREEN -> showRequestTypesScreen(true)
                    Codes.DM_REQUESTS_SCREEN -> showRequestTypesScreen(false)
//                    Codes.MY_REQUESTS_SCREEN -> navigateSafe(RequestsCornerFragmentDirections.actionRequestsCornerFragmentToMyRequestsFragment())
                    Codes.MY_REQUESTS_SCREEN -> activity?.showWarningDialog(getString(R.string.still_working_on_it))
                }
            }
        }
    }

    private fun showRequestTypesScreen(isHr: Boolean) {
        val title =
            if (isHr) getString(R.string.hr_request) else getString(R.string.direct_manager_requests)
        navigateSafe(
            RequestsCornerFragmentDirections.actionRequestsCornerFragmentToRequestsTypesFragment(
                title,
                isHr
            )
        )

    }
}