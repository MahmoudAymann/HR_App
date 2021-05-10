package com.gsgroup.hrapp.ui.fragment.requests.complain

import android.app.Application
import android.net.Uri
import androidx.core.net.toUri
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.*

class ComplainViewModel(app: Application) : AndroidBaseViewModel(app) {

    val request = ComplainRequest()
    val isUrgent = ObservableBoolean()
    val obsIsImageSelected = ObservableBoolean()
    fun gotData(args:ComplainFragmentArgs){
        if(args.isHr){
            request.type = ComplainRequest.HR_REQUEST
        }else
            request.type = ComplainRequest.DM_REQUEST
    }

    fun onImageClick(){
        setValue(Codes.PICK_IMAGE_CODE)
    }

    fun onIsUrgentClick(){
        if(isUrgent.get()){
            isUrgent.set(false)
            request.isUrgent = 0
        }else{
            isUrgent.set(true)
            request.isUrgent = 1
        }
    }

    fun onSubmitClick() {
        if(request.isValid()) {
            requestNewCallDeferred({ complainCallAsync() }) {
                postResult(Resource.success(msg= it.message))
            }
        }
    }


    private fun complainCallAsync() = apiHelper.complainRequestAsync(
        request.details?.toRequestBodyParam(),
        request.isUrgent.toRequestBodyParam(),
        request.type.toRequestBodyParam(),
        request.imageFile?.toMultiPart("image"))


    fun gotImage(uri: String) {
        request.imageFile = uri.stringPathToFile(app)
        obsIsImageSelected.set(true)
    }

}