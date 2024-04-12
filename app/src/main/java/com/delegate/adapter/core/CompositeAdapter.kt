package com.delegate.adapter.core

import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias Delegate = DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>

class CompositeAdapter(
    private val delegates: SparseArray<Delegate>,
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(DelegateAdapterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].createViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        onBindViewHolder(holder, position, mutableListOf())

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        val adapter = delegates[getItemViewType(position)]
            ?: error("can't find adapter for position $position")

        adapter.bindViewHolder(
            model = getItem(position),
            viewHolder = holder,
            payloads = payloads.filterIsInstance<Payloadable>()
        )
    }

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size()) {
            val key = delegates.keyAt(i)
            if (delegates[key].modelClass == getItem(position).javaClass) {
                return key
            }
        }
        throw NullPointerException("Can not get viewType for position $position")
    }

    class Builder {

        private var count: Int = 0
        private val delegates: SparseArray<Delegate> =
            SparseArray()

        @Suppress("UNCHECKED_CAST")
        fun add(delegate: DelegateAdapter<out DelegateAdapterItem, *>): Builder {
            delegates.put(count++, delegate as Delegate)
            return this
        }

        fun build(): CompositeAdapter {
            require(count != 0) { "Register at least one adapter" }
            return CompositeAdapter(delegates)
        }
    }
}