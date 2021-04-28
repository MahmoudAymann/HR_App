package com.gsgroup.hrapp.ui.fragment.news

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class NewsViewModel(app: Application) : AndroidBaseViewModel(app) {

    val adapter = NewsAdapter(::onItemClick)
    val obsShowEmptyView = ObservableBoolean()
    private fun onItemClick(itemNews: ItemNews) {
        setValue(itemNews)
    }

    init {
        requestNewCallDeferred({ newsCall() }) {
            it.response?.data?.let { list ->
                if(list.isEmpty()){
                 obsShowEmptyView.set(true)
                 adapter.clearCurrentList()
                }else {
                    obsShowEmptyView.set(false)
                    adapter.setList(list)
                }
            } ?: obsShowEmptyView.set(true)
            postResult(Resource.success(it))
        }
    }

    private fun newsCall() = apiHelper.getNewsAsync()


}