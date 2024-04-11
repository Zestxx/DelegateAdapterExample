package com.delegate.adapter.example.data

import com.delegate.adapter.core.Payloadable

data class FirstModel(
    val id: Int,
    val label: String,
    val isChecked: Boolean = false,
    val isSwitchEnabled: Boolean = false,
)

fun FirstModel.getPayload(other: FirstModel): Payloadable {
    val result = FirstModelPayload(
        label = if (other.label != label) {
            other.label
        } else null,

        isChecked = if (other.isChecked != isChecked) {
            other.isChecked
        } else null,

        isSwitchEnabled = if (other.isSwitchEnabled != isSwitchEnabled) {
            other.isSwitchEnabled
        } else null,
    )

    return if (result.isEmpty) {
        Payloadable.None
    } else {
        result
    }
}

data class FirstModelPayload(
    val label: String? = null,
    val isChecked: Boolean? = null,
    val isSwitchEnabled: Boolean? = null,
) : Payloadable {
    val isEmpty: Boolean
        get() = isChecked == null
                && label == null
                && isSwitchEnabled == null

}
