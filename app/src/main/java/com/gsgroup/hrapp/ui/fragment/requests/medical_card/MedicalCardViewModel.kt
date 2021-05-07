package com.gsgroup.hrapp.ui.fragment.requests.medical_card


import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel

class MedicalCardViewModel(app: Application) : AndroidBaseViewModel(app) {


    val isNewSelected = ObservableInt(-1)
    val obsNotes = ObservableField<String>()

    fun onButtonClick(isNew:Boolean){
        if(isNew){
            isNewSelected.set(1)
            obsNotes.set(newNotes)
        }else{
            isNewSelected.set(0)
            obsNotes.set(replaceNotes)
        }
    }


    private val newNotes = app.getString(R.string.new_medical_card_notes)
    private val replaceNotes = app.getString(R.string.replacement_medical_card_notes)




}