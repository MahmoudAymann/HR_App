package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class ColorsAdapter(itemClick: (ItemColor) -> Unit={}) : BaseAdapter<ItemColor>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_colors_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<ItemColor>) {

    }
}