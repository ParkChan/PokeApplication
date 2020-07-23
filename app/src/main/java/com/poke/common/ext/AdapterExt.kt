package com.poke.common.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.poke.common.adapter.BaseRecyclerAdapter

@BindingAdapter("replaceItems")
fun replaceItems(recyclerView: RecyclerView, item: List<Any>?) {
    if (item == null) return

    @Suppress("UNCHECKED_CAST")
    (recyclerView.adapter as BaseRecyclerAdapter<Any>).run {
        replaceItems(item)
    }
}