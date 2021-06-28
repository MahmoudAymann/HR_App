package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.app.Application
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import kotlin.properties.Delegates

class MedicalApprovalDetailsViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsMail = ObservableField<String>()
    val obsNotes = ObservableField<String>()
    var type by Delegates.notNull<Int>()

    fun gotData(args: MedicalApprovalDetailsFragmentArgs) {
        this.type = args.type
        when (args.type) {
            Codes.CHRONICAL_MEDICAL -> { obsMail.set(app.getString(R.string.chronical_mail)) }
            Codes.PHYSICAL_MEDICAL, Codes.SURGENT_MEDICAL, Codes.XRAY_MEDICAL, Codes.ANALYSIS_MEDICAL -> {
                obsMail.set(app.getString(R.string.normal_mail))
            }
        }
    }

    fun onMailClick() {
        setValue(type)
    }

}