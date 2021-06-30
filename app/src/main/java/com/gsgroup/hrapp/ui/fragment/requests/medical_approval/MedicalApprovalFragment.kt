package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.databinding.FragmentMedicalApprovalBinding
import com.gsgroup.hrapp.ui.fragment.bottomsheet.BottomSheetFragmentDirections
import com.gsgroup.hrapp.util.*
import com.gsgroup.hrapp.util.AppUtil.openMailIntent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MedicalApprovalFragment :
    BaseFragment<FragmentMedicalApprovalBinding, MedicalApprovalViewModel>() {
    override fun pageTitle(): String = getString(R.string.medical_approval_request)

    override val mViewModel: MedicalApprovalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.NORMAL_MEDICAL -> {
                        navigateSafe(
                            MedicalApprovalFragmentDirections.actionMedicalApprovalFragmentToBottomSheetFragment(
                                listOfItems.toTypedArray(), false
                            )
                        )
                    }
                    Codes.CHRONICAL_MEDICAL -> {
                        navigateSafe(
                            MedicalApprovalFragmentDirections.actionMedicalApprovalFragmentToMedicalApprovalDetailsFragment(
                                getString(R.string.medical_approval_to_treat_a_chronic_disease),
                                Codes.CHRONICAL_MEDICAL
                            )
                        )
                    }

                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message) {
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
//        handleFragRes()
    }

    private fun handleFragRes() {
        listenForResult<SearchItemInterface>(ConstString.RESULT_FROM_BOTTOMSHEET_LIST) {
            mViewModel.gotResultFromBottomSheet(it)
        }
    }
}