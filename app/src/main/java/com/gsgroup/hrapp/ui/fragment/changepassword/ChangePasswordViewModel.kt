package com.gsgroup.hrapp.ui.fragment.changepassword

import androidx.databinding.ObservableBoolean
import androidx.databinding.adapters.TextViewBindingAdapter
import com.gsgroup.hrapp.base.BaseViewModel
import timber.log.Timber

class ChangePasswordViewModel : BaseViewModel() {

    val obsIsPassWrong = ObservableBoolean(true)

    fun onTextChange(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, _, _, _ ->
            obsIsPassWrong.set(s.length < 8)
        }
    }
}