package com.delegate.adapter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.delegate.adapter.core.CompositeAdapter
import com.delegate.adapter.example.data.FirstAdapterItem
import com.delegate.adapter.example.data.FirstModel
import com.delegate.adapter.example.view.FirstAdapter
import com.delegate.adapter.ui.theme.DelegateAdapterExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DelegateAdapterExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }


            val adapter = CompositeAdapter.Builder()
                .add(FirstAdapter())
                .build()

            adapter.submitList(
                buildList {
                    add(
                        FirstAdapterItem(
                            data = FirstModel(id = 0, title = "0", "0000")
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DelegateAdapterExampleTheme {
        Greeting("Android")
    }
}