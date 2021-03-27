package com.gsgroup.hrapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gsgroup.hrapp.BR
import timber.log.Timber

abstract class BaseAdapter<T : BaseParcelable>(
    private val itemClick: (T) -> Unit = {}
) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback()) {

    val listOfItems = arrayListOf<T?>()

    private var position: Int = 0

    @LayoutRes
    abstract fun layoutRes(): Int
    abstract fun onViewHolderCreated(viewHolder: BaseViewHolder<T>)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutRes(),
            parent,
            false
        )
        return BaseViewHolder<T>(binding).apply {
            onViewHolderCreated(this)
            itemView.setOnClickListener {
                this@BaseAdapter.position = adapterPosition
                itemClick(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    fun removeItem(item: T?, isListEmpty: (Boolean) -> Unit = {}) {
        Timber.e("deleting > $item in pos: $position")
        item?.let {
            listOfItems.remove(it)
            submitList(listOfItems.toMutableList())
            isListEmpty(listOfItems.size == 0)
        }
    }

    fun updateList(newList: List<T>) {
        listOfItems.addAll(newList)
        submitList(listOfItems.toMutableList())
    }

    fun setList(newList: List<T?>) {
        listOfItems.clear()
        listOfItems.addAll(newList)
        submitList(listOfItems.toMutableList())
    }

    fun addItemToList(item: T?, isAdded: (Boolean) -> Unit) {
        item?.let {
            listOfItems.add(item)
            submitList(listOfItems.toMutableList())
            isAdded(true)
        } ?: Timber.e("item is null")
    }

    fun editItem(item: T?) {
        item?.let {
            listOfItems[position] = item
            submitList(listOfItems.toMutableList())
        } ?: Timber.e("item is null")
    }

    fun clearCurrentList() {
        listOfItems.clear()
        submitList(listOfItems.toMutableList())
    }

}

class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}

class BaseItemCallback<T : BaseParcelable> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.unique().toString() == newItem.unique().toString()

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem.equals(newItem)
}

interface BaseParcelable {
    fun unique(): Any
}