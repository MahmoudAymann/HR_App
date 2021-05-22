package com.gsgroup.hrapp.ui.fragment.requests.permission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentPermissionBinding
import com.gsgroup.hrapp.util.*

class PermissionFragment : BaseFragment<FragmentPermissionBinding, PermissionViewModel>() {
    override fun pageTitle(): String = getString(R.string.excuse_request)
    override val mViewModel: PermissionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.OPEN_DATE_FROM -> AppUtil.openDatePicker(requireContext()) { date ->
                        onDateSelected(date)
                    }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message) {
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