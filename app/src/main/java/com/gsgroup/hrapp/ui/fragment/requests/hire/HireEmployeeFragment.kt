package com.gsgroup.hrapp.ui.fragment.requests.hire

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentHireEmployeeBinding

class HireEmployeeFragment : BaseFragment<FragmentHireEmployeeBinding, HireEmployeeViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: HireEmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}