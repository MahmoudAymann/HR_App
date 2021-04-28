package com.gsgroup.hrapp.ui.fragment.hr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItem
import com.gsgroup.hrapp.databinding.FragmentHrBinding
import com.gsgroup.hrapp.util.AppUtil.callPhoneNumber
import com.gsgroup.hrapp.util.AppUtil.openMailIntent
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class HRFragment : BaseFragment<FragmentHrBinding, HRViewModel>() {
    override fun pageTitle(): String = getString(R.string.my_hr)
    override val mViewModel: HRViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.BUSINESS_PHONE->activity?.callPhoneNumber(data?.bussniessMobile)
                    Codes.PRIVATE_PHONE->activity?.callPhoneNumber(data?.privateMobile)
                    Codes.EMAIL_CLICK->activity?.openMailIntent(data?.email)
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