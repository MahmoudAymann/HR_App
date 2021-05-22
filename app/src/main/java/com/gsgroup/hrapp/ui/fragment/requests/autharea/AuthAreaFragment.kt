package com.gsgroup.hrapp.ui.fragment.requests.autharea

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.databinding.FragmentAuthAreaBinding
import com.gsgroup.hrapp.ui.fragment.map.MapFragmentDirections
import com.gsgroup.hrapp.util.*

class AuthAreaFragment : BaseFragment<FragmentAuthAreaBinding, AuthAreaViewModel>() {
    override fun pageTitle(): String = getString(R.string.auth_area_request)
    override val mViewModel: AuthAreaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleFragRes()
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.OPEN_CITY_LIST -> navigateSafe(
                        AuthAreaFragmentDirections.actionAuthAreaFragmentToBottomSheetFragment(
                            citiesList
                        )
                    )
                    Codes.OPEN_AREA_LIST -> navigateSafe(
                        AuthAreaFragmentDirections.actionAuthAreaFragmentToBottomSheetFragment(
                            areasList
                        )
                    )
                    Codes.HIDE_PROGRESS->{ showProgress(false) }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message){
                            closeFragment()
                        }
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