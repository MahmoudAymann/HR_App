package com.gsgroup.hrapp.ui.fragment.faq

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class FAQViewModel(app:Application) : AndroidBaseViewModel(app) {

    val adapter = FAQAdapter(::onItemClick)
    val obsShowEmptyView = ObservableBoolean()

    private fun onItemClick(item: FAQItem) {
        setValue(item)
    }


    init {
        requestNewCallDeferred({ faqsCallAsync() }) {
            it.response?.data?.let { lists ->
                if(lists.isEmpty()){
                    obsShowEmptyView.set(true)
                    adapter.clearCurrentList()
                }else {
                    obsShowEmptyView.set(false)
                    adapter.setList(lists)
                }
            } ?: obsShowEmptyView.set(true)
            postResult(Resource.success(it))
        }
    }


    fun onUserGuideClick(){
        setValue(Codes.GUIDE_CLICK)
    }

    private  fun faqsCallAsync() = apiHelper.getFaqsAsync()

}