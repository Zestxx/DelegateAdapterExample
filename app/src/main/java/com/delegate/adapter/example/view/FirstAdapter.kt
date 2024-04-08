package com.delegate.adapter.example.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delegate.adapter.R
import com.delegate.adapter.core.DelegateAdapter
import com.delegate.adapter.core.Payloadable
import com.delegate.adapter.example.data.FirstAdapterItem

class FirstAdapter :
    DelegateAdapter<FirstAdapterItem, FirstViewHolder>(FirstAdapterItem::class.java) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return FirstViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.first_item, parent, false)
        )
    }

    override fun bindViewHolder(
        model: FirstAdapterItem,
        viewHolder: FirstViewHolder,
        payloads: List<Payloadable>,
    ) {
        viewHolder.bind(model.data, payloads)
    }
}