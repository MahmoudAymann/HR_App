package com.gsgroup.hrapp.ui.fragment.changepassword

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.adapters.TextViewBindingAdapter
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class ChangePasswordViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsIsPassWrong = ObservableBoolean()
    val request = ChangePasswordRequest()
    var userId : String? = null
    fun onSubmitClick() {
        if (request.isValid()) {
            requestNewCallDeferred({ changePasswordCallAsync() }) {
                postResult(Resource.success(it))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun changePasswordCallAsync() = apiHelper.changePasswordAsync(request)

    fun onTextChange(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, _, _, _ ->
            obsIsPassWrong.set(s.length < 8)
        }
    }


    fun gotData(args:ChangePasswordFragmentArgs){
        args.userId?.let {
            userId =  args.userId
        }
        notifyChange()
    }



}