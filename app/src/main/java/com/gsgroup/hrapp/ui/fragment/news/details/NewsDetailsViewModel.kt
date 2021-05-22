package com.gsgroup.hrapp.ui.fragment.news.details

import android.app.Application
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.ui.fragment.news.NewsItem
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class NewsDetailsViewModel(app:Application) : AndroidBaseViewModel(app) {

    var data: NewsItem? = null


    fun getData(id: Int){
        requestNewCallDeferred({ newsDetailsCall(id) }) {
            it.response?.data?.let {nData->
                data = nData
                notifyChange()
                postResult(Resource.success(it))
            }?:postResult(Resource.message(it.message))
        }
    }



    private fun newsDetailsCall(id:Int) = apiHelper.getNewsDetailsAsync(id)

}