package com.gsgroup.hrapp.ui.fragment.faq

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder
import com.gsgroup.hrapp.databinding.ItemFaqViewBinding

class FAQAdapter(itemClick: (FAQItem) -> Unit, ) : BaseAdapter<FAQItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_faq_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<FAQItem>) {

    }
}