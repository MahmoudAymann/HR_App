package com.gsgroup.hrapp.ui.fragment.requests.mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentMissionBinding
import com.gsgroup.hrapp.util.*

class MissionFragment : BaseFragment<FragmentMissionBinding, MissionViewModel>() {
    override fun pageTitle(): String = getString(R.string.mission_request)
    override val mViewModel: MissionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.OPEN_DATE_FROM->AppUtil.openDatePicker(requireContext()){date->
                        onDateSelected(it as Int, date)
                    }
                    Codes.OPEN_DATE_TO->AppUtil.openDatePicker(requireContext(), request.fromDate){date->
                        onDateSelected(it as Int, date)
                    }

                    Codes.OPEN_TIME_FROM->AppUtil.openTimePicker(requireContext()){calendar->
                        onTimeSelected(it as Int, calendar)
                    }
                    Codes.OPEN_TIME_TO->AppUtil.openTimePicker(requireContext()){calendar->
                        onTimeSelected(it as Int, calendar)
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