package com.gsgroup.hrapp.ui.fragment.requests.permission

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class PermissionViewModel(app: Application) : AndroidBaseViewModel(app) {

    val request = PermissionRequestRequest()
    val obsIsMorning = ObservableInt(-1)

    fun onTimeSelect(isMorning: Boolean) {
        if (isMorning) {
            obsIsMorning.set(1)
            request.type = PermissionRequestRequest.TYPE_MORNING
        } else {
            obsIsMorning.set(0)
            request.type = PermissionRequestRequest.TYPE_EVENING
        }
    }

    fun onDateClick() {
        setValue(Codes.OPEN_DATE_FROM)
    }

    fun onSubmitClick() {
        if (request.isValid())
            requestNewCallDeferred({ permissionRequestDefAsync() }) {
                postResult(Resource.success(msg = it.message))
            }
    }

    private fun permissionRequestDefAsync() = apiHelper.permissionRequestAsync(request)

    fun onDateSelected(date: String) {
        request.permissionDay = date
        notifyChange()
    }
}