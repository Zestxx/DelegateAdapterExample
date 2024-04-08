package com.delegate.adapter.core

interface DelegateAdapterItem {
    fun id(): Any
    fun content(): Int
    fun payload(other: Any): Payloadable = Payloadable.None
}