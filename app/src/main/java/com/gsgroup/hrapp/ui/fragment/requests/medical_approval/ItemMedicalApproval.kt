package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.content.Context
import android.os.Parcel
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.ui.fragment.bottomsheet.SearchAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemMedicalApproval(var id: Int, var name: String? = null) : SearchItemInterface {
    companion object{
        fun getDummyData(context: Context) = arrayListOf(
            ItemMedicalApproval(Codes.XRAY_MEDICAL, context.getString(R.string.xray_approval)),
            ItemMedicalApproval(Codes.ANALYSIS_MEDICAL, context.getString(R.string.analysis_approval)),
            ItemMedicalApproval(Codes.SURGENT_MEDICAL, context.getString(R.string.surgent_approval)),
            ItemMedicalApproval(Codes.PHYSICAL_MEDICAL, context.getString(R.string.physical_approval))
        )
    }

    override fun id(): Int  = id

    override fun name(): String? = name
}