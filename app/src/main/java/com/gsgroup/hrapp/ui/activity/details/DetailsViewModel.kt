package com.gsgroup.hrapp.ui.activity.details

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.constants.Codes

class DetailsViewModel(app: Application) : AndroidBaseViewModel(app) {

    val obsTitle = ObservableField<String>()
    val obsShowBackButton = ObservableBoolean()
    var showProgressBar = ObservableBoolean()
    val obsShowHeaderView = ObservableBoolean()
    val obsShowSheet = ObservableBoolean()

//    val adapter = SearchAdapter(::onItemClick)
//
//    private fun onItemClick(searchItem: SearchItem) {
//        Timber.e("$searchItem")
//    }

    init {
//        adapter.setList(SearchItem.getDummyData())
//        notifyChange()
    }

    fun onBackClick() {
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun setScreenPermissions(id: Int) {
        when (id) {
            R.id.splashFragment, R.id.loginFragment -> authScreenPermissions()
            else -> detailsScreen()
        }
    }

    private fun authScreenPermissions() { //hide all headers
        obsShowBackButton.set(false)
        obsShowHeaderView.set(false)
    }

    private fun detailsScreen() {// show back button with logo in toolbar
        obsShowBackButton.set(true)
        obsShowHeaderView.set(true)
    }


}