package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes

class MedicalApprovalViewModel : BaseViewModel() {



    fun onButtonClick(isNormal: Boolean) {
        if (isNormal)
            setValue(Codes.NORMAL_MEDICAL)
        else
            setValue(Codes.CHRONICAL_MEDICAL)
    }


}