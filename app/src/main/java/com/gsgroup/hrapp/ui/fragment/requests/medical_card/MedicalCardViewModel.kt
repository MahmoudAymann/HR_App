package com.gsgroup.hrapp.ui.fragment.requests.medical_card


import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class MedicalCardViewModel(app: Application) : AndroidBaseViewModel(app) {


    val isNewSelected = ObservableInt(-1)
    val obsNotes = ObservableField<String>()
    val request = MedicalCardRequestRequest()
    fun onButtonClick(isNew:Boolean){
        if (isNew) {
            isNewSelected.set(1)
            obsNotes.set(newNotes)
            request.type = MedicalCardRequestRequest.TYPE_NEW
        } else {
            isNewSelected.set(0)
            obsNotes.set(replaceNotes)
            request.type = MedicalCardRequestRequest.TYPE_REPLACEMENT
        }
    }


    fun onSubmitClick() {
        if (request.type == null) return
        requestNewCallDeferred({ medicalCardRequestDefAsync() }) {
            postResult(Resource.success(msg = it.message))
        }
    }

    private fun medicalCardRequestDefAsync() = apiHelper.medicalCardRequestAsync(request)

    private val newNotes = app.getString(R.string.new_medical_card_notes)
    private val replaceNotes = app.getString(R.string.replacement_medical_card_notes)


}