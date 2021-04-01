package com.gsgroup.hrapp.ui.fragment.requests.vacation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentVacationBinding

class VacationFragment : BaseFragment<FragmentVacationBinding, VacationViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: VacationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}