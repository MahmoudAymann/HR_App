package com.gsgroup.hrapp.ui.fragment.myteam.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.databinding.FragmentTeamFilterBinding
import com.gsgroup.hrapp.ui.fragment.map.MapFragmentDirections
import com.gsgroup.hrapp.util.*

class TeamFilterFragment : BaseFragment<FragmentTeamFilterBinding, TeamFilterViewModel>() {
    override fun pageTitle(): String = getString(R.string.filter_my_team)
    override val mViewModel: TeamFilterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleFragRes()

        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.OPEN_CITY_LIST -> navigateSafe(
                        MapFragmentDirections.actionMapFragmentToBottomSheetFragment(citiesList))
                    Codes.OPEN_AREA_LIST -> navigateSafe(
                        MapFragmentDirections.actionMapFragmentToBottomSheetFragment(areasList))
                    Codes.OPEN_JT_LIST -> navigateSafe(
                        MapFragmentDirections.actionMapFragmentToBottomSheetFragment(jobTitlesList))

                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }
        }
    }


    private fun handleFragRes() {
        listenForResult<SearchItemInterface>(ConstString.RESULT_FROM_BOTTOMSHEET_LIST) {
            mViewModel.gotResultFromBottomSheet(it)
        }
    }


}