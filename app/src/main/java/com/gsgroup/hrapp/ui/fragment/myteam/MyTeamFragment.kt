package com.gsgroup.hrapp.ui.fragment.myteam

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentMyTemBinding
import com.gsgroup.hrapp.util.*

class MyTeamFragment : BaseFragment<FragmentMyTemBinding, MyTeamViewModel>() {
    override fun pageTitle(): String = getString(R.string.my_team)
    override val mViewModel: MyTeamViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.FILTER_SCREEN -> activity?.showWarningDialog(getString(R.string.still_working_on_it))
                    Codes.CHANGE_PASSWORD_SCREEN->navigateSafe(MyTeamFragmentDirections.actionMyTeamFragmentToChangePasswordFragment(item.id.toString()))
                    Codes.ATTENDANCE_LOGS_SCREEN->navigateSafe(MyTeamFragmentDirections.actionMyTeamFragmentToSignInOutLogFragment(item.id.toString()))
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