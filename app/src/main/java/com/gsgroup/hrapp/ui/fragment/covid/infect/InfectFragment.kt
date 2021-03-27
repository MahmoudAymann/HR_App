package com.gsgroup.hrapp.ui.fragment.covid.infect

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentInfectBinding

class InfectFragment : BaseFragment<FragmentInfectBinding, InfectViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: InfectViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}