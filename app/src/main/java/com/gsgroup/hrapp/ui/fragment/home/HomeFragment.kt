package com.gsgroup.hrapp.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.HomeIds
import com.gsgroup.hrapp.data.model.HomeIds.*
import com.gsgroup.hrapp.databinding.FragmentHomeBinding
import com.gsgroup.hrapp.ui.fragment.map.MapActivity
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showActivity


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun pageTitle(): String = getString(R.string.home)

    override val mViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    is HomeIds -> openScreens(it)
                    Codes.MAP_SCREEN -> activity?.showActivity<MapActivity>()
                }
            }
        }
    }

    private fun openScreens(it: HomeIds) {
        when (it) {
            DIRECT_MANAGER -> showDetailsActivity(R.id.directManagerFragment)
            REQUESTS -> showDetailsActivity(R.id.requestsCornerFragment)
            SIGN_IN_OUT_LOGS -> showDetailsActivity(R.id.signInOutLogFragment)
            HR -> showDetailsActivity(R.id.HRFragment)
            MY_TEAM -> showDetailsActivity(R.id.myTeamFragment)
            COMPANY_POLICES -> showDetailsActivity(R.id.companyPoliciesFragment)
        }
    }


}

