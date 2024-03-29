package com.studyfirstproject.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B : ViewDataBinding, T>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?,
    private val bindingVariableId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false)
) {
    private val _binding: B = DataBindingUtil.bind(itemView)!!

    val binding
        get() = _binding

    fun bind(item: T) {
        try {
            binding.run {
                setVariable(bindingVariableId, item)
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}