package com.gsgroup.hrapp.ui.fragment.news

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class NewsAdapter(itemClick: (NewsItem) -> Unit) : BaseAdapter<NewsItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_news_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<NewsItem>) {

    }
}