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

    fun onSubmitClick() {
        if (request.isValid()) {
            requestNewCallDeferred({ changePasswordCall(request) }) {
                postResult(Resource.success(it))
            }
        } else {
            postResult(Resource.message(app.getString(R.string.all_data_required)))
        }
    }

    private fun changePasswordCall(request: ChangePasswordRequest) =
        apiHelper.changePasswordAsync(request)

    fun onTextChange(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, _, _, _ ->
            obsIsPassWrong.set(s.length < 8)
        }
    }
}