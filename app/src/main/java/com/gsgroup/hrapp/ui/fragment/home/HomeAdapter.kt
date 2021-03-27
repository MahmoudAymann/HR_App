package com.gsgroup.hrapp.ui.fragment.home

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder
import com.gsgroup.hrapp.data.model.HomeItem

class HomeAdapter(itemClick: (HomeItem) -> Unit) : BaseAdapter<HomeItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_home_view_main
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<HomeItem>) {

    }
}