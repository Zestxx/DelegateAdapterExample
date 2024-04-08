package com.delegate.adapter.example.data

import com.delegate.adapter.core.Payloadable

data class FirstModel(
    val id: Int,
    val title: String,
    val description: String,
)

fun FirstModel.getPayload(other: FirstModel): Payloadable {
    val updateFields = buildMap<String, Any> {
        if (other.title != title) {
            put("title", other.title)
        }

        if (other.description != description) {
            put("description", other.description)
        }
    }
    return if (updateFields.isEmpty())
        Payloadable.None
    else
        FirstModelPayload(fields = updateFields)
}

data class FirstModelPayload(val fields: Map<String, Any>) : Payloadable
