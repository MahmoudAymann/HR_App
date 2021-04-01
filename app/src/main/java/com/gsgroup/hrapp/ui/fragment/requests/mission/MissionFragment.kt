package com.gsgroup.hrapp.ui.fragment.requests.mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentMissionBinding

class MissionFragment : BaseFragment<FragmentMissionBinding, MissionViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: MissionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}