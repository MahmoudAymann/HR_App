package com.gsgroup.hrapp.ui.fragment.requests.types

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel

class RequestsTypesViewModel(app: Application) : AndroidBaseViewModel(app) {

    val adapter = RequestTypeAdapter(::onItemClick)

    private fun onItemClick(item: ItemRequestType) {
        setValue(item)
    }

    fun gotData(args: RequestsTypesFragmentArgs){
        if(args.isHr)
            adapter.setList(ItemRequestType.getHRRequests(app.applicationContext))
        else
            adapter.setList(ItemRequestType.getDMRequests(app.applicationContext))
    }


}