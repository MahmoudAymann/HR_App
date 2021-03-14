package com.gsgroup.hrapp.ui.fragment.map

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentMapBinding
import com.gsgroup.hrapp.databinding.FragmentSalaryBinding
import com.gsgroup.hrapp.ui.fragment.salary.SalaryViewModel

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {
    override fun pageTitle(): String? = null
    override val mViewModel: MapViewModel by viewModels()

}