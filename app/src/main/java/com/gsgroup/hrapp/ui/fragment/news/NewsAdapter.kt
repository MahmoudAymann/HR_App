package com.gsgroup.hrapp.ui.fragment.news

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.databinding.ItemNewsViewBinding
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import com.gsgroup.hrapp.util.SharedPrefUtil
import com.gsgroup.hrapp.util.SharedPrefUtil.sharedPrefs
import com.gsgroup.hrapp.util.loadImageFromURL

class NewsAdapter(itemClick: (NewsItem) -> Unit) : BaseAdapter<NewsItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_news_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<NewsItem>) {
        (viewHolder.binding as ItemNewsViewBinding).apply {
            val userData by image.context.sharedPrefs<DataUser>(ConstString.PREF_USER_DATA)
            userData?.company?.image?.let {
                image.loadImageFromURL(it)
            }
            userData?.company?.name?.let {
                tvName.text = it
            }

        }
    }
}