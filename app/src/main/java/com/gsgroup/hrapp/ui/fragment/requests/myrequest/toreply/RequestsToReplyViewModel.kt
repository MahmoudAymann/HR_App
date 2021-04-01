package com.gsgroup.hrapp.ui.fragment.requests.myrequest.toreply

import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.BaseViewModel

class RequestsToReplyViewModel : BaseViewModel() {

    val obsChangeSelectedBackgroundToNew = ObservableBoolean(true)
    val obsIsNewNow = ObservableBoolean(true)
    private var selectedNew = true
    fun onSelectClick(isNew: Boolean) {
        if (isNew) {
            if (!selectedNew) {
                obsIsNewNow.set(true)
                selectedNew = true
                obsChangeSelectedBackgroundToNew.set(true)
            }
        } else {
            if (selectedNew) {
                obsIsNewNow.set(false)
                selectedNew = false
                obsChangeSelectedBackgroundToNew.set(false)
            }
        }
        val url = if (isNew) "get new" else "get old"
    }

    fun onNotificationClick(isNew: Boolean) {
        if (isNew) {
            if (!selectedNew) {
                obsIsNewNow.set(true)
                selectedNew = true
                obsChangeSelectedBackgroundToNew.set(true)
            }
        } else {
            if (selectedNew) {
                obsIsNewNow.set(false)
                selectedNew = false
                obsChangeSelectedBackgroundToNew.set(false)
            }
        }
        val url = if (isNew) "get new" else "get old"
    }

}