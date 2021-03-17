package com.gsgroup.hrapp.ui.fragment.faq

import com.gsgroup.hrapp.base.BaseViewModel

class FAQViewModel : BaseViewModel() {

    val adapter = FAQAdapter(::onItemClick)

    private fun onItemClick(item: FAQItem) {


    }


    init {
        adapter.setList(FAQItem.getDummyList())
    }


    fun onUserGuideClick(){

    }
}