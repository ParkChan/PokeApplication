package com.poke.common.adapter

import android.util.ArrayMap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(
    parent: ViewGroup,
    private val viewHolderResData: ViewHolderIdData,
    private val viewModels: ArrayMap<Int, ViewModel>? = null
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(viewHolderResData.layoutId, parent, false)
) {
    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    open fun bindViewHolder(item: Any?, position: Int) {
        if (item == null) return
        binding.setVariable(viewHolderResData.modelId, item)
        binding.setVariable(viewHolderResData.positionId, position)

        if (viewModels == null) return
        for (key in viewModels.keys) {
            binding.setVariable(key, viewModels[key])
        }
    }
}