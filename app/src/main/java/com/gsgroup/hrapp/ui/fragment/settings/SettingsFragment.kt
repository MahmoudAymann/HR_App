package com.gsgroup.hrapp.ui.fragment.settings

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: SettingsViewModel by viewModels()
}