package com.delegate.adapter.core

import androidx.recyclerview.widget.DiffUtil

class DelegateAdapterItemDiffCallback : DiffUtil.ItemCallback<DelegateAdapterItem>() {
    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem,
    ): Boolean = oldItem::class == newItem::class && oldItem.id() == newItem.id()

    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem,
    ): Boolean = oldItem.content() == newItem.content()

    override fun getChangePayload(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem,
    ): Any = oldItem.payload(newItem)
}