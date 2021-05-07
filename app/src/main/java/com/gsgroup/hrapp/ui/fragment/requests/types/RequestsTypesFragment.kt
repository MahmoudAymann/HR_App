package com.gsgroup.hrapp.ui.fragment.requests.types

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentRequestsTypesBinding
import com.gsgroup.hrapp.ui.fragment.requests.types.RequestTypes.*
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class RequestsTypesFragment : BaseFragment<FragmentRequestsTypesBinding, RequestsTypesViewModel>() {

    val args by navArgs<RequestsTypesFragmentArgs>()

    override fun pageTitle(): String = args.title
    override val mViewModel: RequestsTypesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            gotData(args)
            observe(mutableLiveData) {
                when (it) {
                    is ItemRequestType -> openAdapterScreens(it)
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

    private fun openAdapterScreens(item: ItemRequestType) {
        when (item.id) {
            PHONE_ISSUE -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToPhoneIssueFragment())
            SALARY_INFO -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToSalaryInfoFragment())
            HR_COMPLAIN -> navigateSafe(
                RequestsTypesFragmentDirections.actionRequestsTypesFragmentToComplainFragment(
                    true
                )
            )
            PRINT_INSURANCE -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToPrintInsuranceFragment())
            MEDICAL_CARD -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToMedicalCardFragment())
            MEDICAL_APPROVAL -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToMedicalApprovalFragment())
            AUTH_AREA -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToAuthAreaFragment())
            BORROW -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToFragmentBorrow())
            EXP_CERTIFICATE -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToExpCertificateFragment())
            HIRE_EMPLOYEE -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToHireEmployeeFragment())
            RESIGN -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToResignFragment())
            PENALTY -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToPenaltyFragment())
            VACATION -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToVacationFragment())
            EXCUSE -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToPermissionFragment())
            MISSION -> navigateSafe(RequestsTypesFragmentDirections.actionRequestsTypesFragmentToMissionFragment())
            DM_COMPLAIN -> navigateSafe(
                RequestsTypesFragmentDirections.actionRequestsTypesFragmentToComplainFragment(
                    false
                )
            )
        }
    }
}