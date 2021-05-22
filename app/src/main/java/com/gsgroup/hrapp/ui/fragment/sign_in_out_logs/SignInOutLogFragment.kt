package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.databinding.FragmentSignInOutLogBinding
import com.gsgroup.hrapp.util.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SignInOutLogFragment : BaseFragment<FragmentSignInOutLogBinding, SignInOutLogViewModel>() {
    override fun pageTitle(): String = getString(R.string.sign_in_out_logs)
    override val mViewModel: SignInOutLogViewModel by viewModels()
    val args : SignInOutLogFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleFragRes()
        mViewModel.apply {
            gotData(args)
            observe(mutableLiveData) {
                when (it) {
                    Codes.SELECT_MONTH -> navigateSafe(
                        SignInOutLogFragmentDirections.actionSignInOutLogFragmentToBottomSheetFragment(
                            ItemMonth.getMonthList(requireContext())
                        )
                    )
                    Codes.SELECT_YEAR ->navigateSafe(
                        SignInOutLogFragmentDirections.actionSignInOutLogFragmentToBottomSheetFragment(
                            ItemYear.getYearsList(requireContext())
                        )
                    )
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