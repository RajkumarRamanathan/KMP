package com.example.genai_chatbot

// shared/src/commonMain/kotlin/com/example/genai_chatbot/App.kt

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope

interface FilePicker {
    fun pickFile()
    var fileUri: UIUpdateCallback?
}

interface UIUpdateCallback {
    fun updateUI(message: String)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  PickAFileUI(filePicker: FilePicker) {
    KoinContext {
        var filePath by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("PDF ChatBot") }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Button(
                        onClick = {
                            filePicker.fileUri = object : UIUpdateCallback {
                                override fun updateUI(message: String) {
                                    filePath = message
                                }
                            }
                            filePicker.pickFile()
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Select PDF")
                    }
                    displayFile(filePath)

                }

                val viewModel = koinViewModel<MainViewModel>()
                val timer by viewModel.timer.collectAsState()
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text =timer.toString()
                    )
                }


            }
        )
    }
}

@Composable
fun displayFile(message: String) {
    Text("Upload PDF")
    Text(message, modifier = Modifier.padding(16.dp))
}

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}
