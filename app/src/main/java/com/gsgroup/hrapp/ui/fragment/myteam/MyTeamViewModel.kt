package com.gsgroup.hrapp.ui.fragment.myteam

import android.app.Application
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes

class MyTeamViewModel( app: Application) : AndroidBaseViewModel(app) {

    val adapter = MyTeamAdapter(::onItemClick)
    val obsCount = ObservableInt()


    private fun onItemClick(myTeamItem: MyTeamItem) {

    }

    init {
        adapter.setList(MyTeamItem.getDummyList())
        obsCount.set(adapter.mCurrentList.size)
    }


    fun onFilterClick(){
        setValue(Codes.FILTER_SCREEN)
    }

}