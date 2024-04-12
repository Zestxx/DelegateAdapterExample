package com.delegate.adapter.example.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.delegate.adapter.core.Payloadable
import com.delegate.adapter.databinding.FirstItemBinding
import com.delegate.adapter.example.data.FirstModel
import com.delegate.adapter.example.data.FirstModelPayload

class FirstViewHolder(
    itemView: View,
    private val onItemChanged: (FirstModel) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    private val binding = FirstItemBinding.bind(itemView)
    private var model: FirstModel? = null

    init {
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            model?.let {
                onItemChanged(
                    it.copy(isChecked = isChecked)
                )
            }
        }

        binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
            model?.let {
                onItemChanged(
                    it.copy(isSwitchEnabled = isChecked)
                )
            }
        }
    }

    fun bind(model: FirstModel, payload: List<Payloadable>) {
        this.model = model
        val modelPayload = payload.filterIsInstance<FirstModelPayload>().firstOrNull()
        if (modelPayload == null) {
            renderFull(model)
        } else {
            renderWithPayload(modelPayload)
        }
    }

    private fun renderFull(model: FirstModel) {
        binding.labelTextView.text = model.label
        binding.checkbox.isChecked = model.isChecked
        binding.switchButton.isChecked = model.isSwitchEnabled
    }

    private fun renderWithPayload(payload: FirstModelPayload) {
        payload.label?.let {
            binding.labelTextView.text = it
        }

        payload.isChecked?.let {
            binding.checkbox.isChecked = it
        }

        payload.isSwitchEnabled?.let {
            binding.switchButton.isChecked = it
        }
    }
}