package com.gsgroup.hrapp.ui.fragment.requests.hire.new

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentHireEmployeeBinding
import com.gsgroup.hrapp.databinding.FragmentNewEmployeeBinding

class NewEmployeeFragment : BaseFragment<FragmentNewEmployeeBinding, NewEmployeeViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: NewEmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}