package com.gsgroup.hrapp.ui.fragment.requests.types

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class RequestTypeAdapter(itemClick: (ItemRequestType) -> Unit) :
    BaseAdapter<ItemRequestType>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_request_type_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<ItemRequestType>) {

    }
}