package com.example.genai_chatbot

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class AndroidFilePicker(activity: ComponentActivity) : FilePicker {
    private val pickFileLauncher =
        activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                // Handle the picked file URI
            }
        }

    override fun pickFile() {
        pickFileLauncher.launch("application/pdf")
    }
}

@Composable
fun rememberFilePicker(): FilePicker {
    val context = LocalContext.current
    return remember { AndroidFilePicker(context as ComponentActivity) }
}