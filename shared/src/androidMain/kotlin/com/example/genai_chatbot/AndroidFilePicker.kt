package com.example.genai_chatbot

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class AndroidFilePicker(activity: ComponentActivity) : FilePicker {
    private val pickFileLauncher =
        activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                // Handle the picked file URI
                fileUri?.updateUI(it.toString())
            }
        }

    override fun pickFile() {
        pickFileLauncher.launch("application/pdf")
    }

    override var fileUri: UIUpdateCallback? = null
}