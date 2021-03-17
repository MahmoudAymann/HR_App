package com.gsgroup.hrapp.ui.fragment.notification

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.base.BaseViewModel

class NotificationViewModel : BaseViewModel() {

    val obsIsNewNow = ObservableBoolean(true)
    val adapter = NotificationAdapter(::itemClick)
    private var selectedNew = true
    val obsChangeSelectedBackgroundToNew = ObservableBoolean(true)
    private fun itemClick(item: NotificationItem) {

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


    init {
        adapter.setList(NotificationItem.getDummyList())
    }
}