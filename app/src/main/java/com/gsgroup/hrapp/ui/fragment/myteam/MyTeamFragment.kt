package com.gsgroup.hrapp.ui.fragment.myteam

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentMyTemBinding

class MyTeamFragment : BaseFragment<FragmentMyTemBinding, MyTeamViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: MyTeamViewModel by viewModels()
}