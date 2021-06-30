package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItemInterface

class MedicalApprovalViewModel(app: Application) : AndroidBaseViewModel(app) {

    val listOfItems = ItemMedicalApproval.getDummyData(app)

    fun onButtonClick(isNormal: Boolean) {
        if (isNormal)
            setValue(Codes.NORMAL_MEDICAL)
        else
            setValue(Codes.CHRONICAL_MEDICAL)
    }

    fun gotResultFromBottomSheet(it: SearchItemInterface?) {
        setValue(it?.id())
    }


}