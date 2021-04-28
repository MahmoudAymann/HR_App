package com.gsgroup.hrapp.ui.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentSettingsBinding
import com.gsgroup.hrapp.ui.fragment.home.HomeFragmentDirections
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.SharedPrefUtil.deleteAllSharedPrefData
import com.gsgroup.hrapp.util.SharedPrefUtil.sharedPrefs

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {
    override fun pageTitle(): String = getString(R.string.settings)
    override val mViewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.RESTART_APP -> activity?.restartApp()
                    Codes.CHANGE_LANG -> activity?.showDialog(getString(R.string.ask_change_language)){ mViewModel.changeLang() }
                    Codes.CHANGE_PASSWORD_SCREEN -> showDetailsActivity(R.id.changePasswordFragment)
                    Codes.LOG_OUT -> logout()
                }
            }
        }
    }

    private fun logout() {
        activity?.showLogoutDialog {
            showDetailsActivity(R.id.loginFragment)
            activity?.deleteAllSharedPrefData()
            activity?.finishAffinity()
        }
    }
}