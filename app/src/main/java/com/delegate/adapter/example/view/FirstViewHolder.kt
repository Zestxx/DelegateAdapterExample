package com.delegate.adapter.example.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.delegate.adapter.core.Payloadable
import com.delegate.adapter.databinding.FirstItemBinding
import com.delegate.adapter.example.data.FirstModel
import com.delegate.adapter.example.data.FirstModelPayload

class FirstViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val binding = FirstItemBinding.bind(itemView)

    fun bind(model: FirstModel, payload: List<Payloadable>) {
        val modelPayload = payload.filterIsInstance<FirstModelPayload>().firstOrNull()
        if (modelPayload == null) {
            renderFull(model)
        } else {
            renderWithPayload(modelPayload)
        }
    }

    private fun renderFull(model: FirstModel) {
        binding.title.text = model.title
        binding.description.text = model.description
    }

    private fun renderWithPayload(payload: FirstModelPayload) {
        payload.fields["title"]?.let {
            binding.title.text = it.toString()
        }

        payload.fields["description"]?.let {
            binding.description.text = it.toString()
        }
    }
}