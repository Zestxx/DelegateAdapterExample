package com.delegate.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delegate.adapter.core.CompositeAdapter
import com.delegate.adapter.databinding.ActivityExampleBinding
import com.delegate.adapter.example.data.FirstAdapterItem
import com.delegate.adapter.example.data.FirstModel
import com.delegate.adapter.example.view.FirstAdapter

class ExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExampleBinding

    private val adapter by lazy {
        CompositeAdapter.Builder()
            .add(FirstAdapter(::handleFirstAdapterItemChanged))
            .build()
    }

    private fun handleFirstAdapterItemChanged(model: FirstModel) {
        // replace the old model
        val newList = adapter.currentList.map {
            if (it.id() == model.id) {
                FirstAdapterItem(data = model.copy(
                    label = "label #${model.id}, [${model.isChecked}, ${model.isSwitchEnabled}]"
                ))
            } else {
                it
            }
        }
        adapter.submitList(newList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        adapter.submitList(
            listOf(
                FirstModel(id = 0, label = "label - 0", false, isSwitchEnabled = false),
                FirstModel(id = 1, label = "label - 1", false, isSwitchEnabled = false),
                FirstModel(id = 2, label = "label - 2", false, isSwitchEnabled = false),
            ).map { FirstAdapterItem(data = it) })
    }
}