package com.delegate.adapter.core

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter<Model, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out Model>) {
    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun bindViewHolder(
        model: Model,
        viewHolder: VH,
        payloads: List<Payloadable>,
    )
}