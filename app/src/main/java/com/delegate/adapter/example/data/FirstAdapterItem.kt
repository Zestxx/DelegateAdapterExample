package com.delegate.adapter.example.data

import com.delegate.adapter.core.DelegateAdapterItem
import com.delegate.adapter.core.Payloadable

data class FirstAdapterItem(val data: FirstModel) : DelegateAdapterItem {

    override fun id(): Any = data.id

    override fun content(): Int = data.hashCode()

    override fun payload(other: Any): Payloadable {
        return (other as? FirstModel)
            ?.let { data.getPayload(other) }
            ?: Payloadable.None
    }
}
