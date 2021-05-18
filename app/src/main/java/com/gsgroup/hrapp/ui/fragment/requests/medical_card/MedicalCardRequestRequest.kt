package com.gsgroup.hrapp.ui.fragment.requests.medical_card

import android.os.Parcelable
import com.gsgroup.hrapp.base.BaseParcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicalCardRequestRequest(
    var type:String?=null
) : Parcelable{


    companion object{
        const val TYPE_NEW = "new"
        const val TYPE_REPLACEMENT = "lost_replacement"
    }
}