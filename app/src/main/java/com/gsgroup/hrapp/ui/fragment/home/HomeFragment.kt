package com.gsgroup.hrapp.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.HomeIds
import com.gsgroup.hrapp.data.model.HomeIds.*
import com.gsgroup.hrapp.databinding.FragmentHomeBinding
import com.gsgroup.hrapp.util.observe


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun pageTitle(): String = getString(R.string.home)

    override val mViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    is HomeIds -> openScreens(it)
                    Codes.CHECK_IN -> showDetailsActivity(R.id.mapFragment, getIntentForMap(true))
                    Codes.CHECK_OUT -> showDetailsActivity(R.id.mapFragment, getIntentForMap(false))
                }
            }
        }
    }

    private fun getIntentForMap(isCheckIn: Boolean): Intent {
        return Intent().putExtra(ConstString.BUNDLE_FRAGMENT,
            Bundle().apply { putBoolean("isCheckIn", isCheckIn) })
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


    override fun onResume() {
        mViewModel.hideCheckInButton()
        super.onResume()
    }

}

