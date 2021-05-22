package com.gsgroup.hrapp.ui.fragment.myteam

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.Resource
import com.gsgroup.hrapp.util.requestNewCallDeferred

class MyTeamViewModel( app: Application) : AndroidBaseViewModel(app) {

    val adapter = MyTeamAdapter(::onChangePasswordClick, ::onLogsClick)
    val obsShowEmpty = ObservableBoolean()
    lateinit var item : MyTeamItem
    private fun onLogsClick(item: MyTeamItem) {
            this.item = item
        setValue(Codes.ATTENDANCE_LOGS_SCREEN)
    }

    private fun onChangePasswordClick(item: MyTeamItem) {
        this.item = item
        setValue(Codes.CHANGE_PASSWORD_SCREEN)
    }

    val obsCount = ObservableInt()


    private fun getMyTeamAsync() = apiHelper.getMyTeamAsync()


    fun onFilterClick(){
        setValue(Codes.FILTER_SCREEN)
    }


    init {
        requestNewCallDeferred({getMyTeamAsync()}){
            postResult(Resource.success())
            it.response?.data?.let { list ->
                obsCount.set(list.size)
                adapter.setList(list)
                if (list.isEmpty()) {
                    obsShowEmpty.set(true)
                } else{
                    obsShowEmpty.set(false)
                }
            }?:obsShowEmpty.set(true)
        }
    }

}