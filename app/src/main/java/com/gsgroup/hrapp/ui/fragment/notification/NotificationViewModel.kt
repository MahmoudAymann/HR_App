package com.gsgroup.hrapp.ui.fragment.notification

import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.BaseViewModel

class NotificationViewModel : BaseViewModel() {

    val obsIsNewSelected = ObservableBoolean(true)
    val obsIsNew = ObservableBoolean(true)

    fun onClick(isNew: Boolean) {
        obsIsNewSelected.set(isNew)
        obsIsNew.set(isNew)
        val url = if (isNew) "get new" else "get old"
    }
}