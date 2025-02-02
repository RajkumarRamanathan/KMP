// androidApp/src/main/java/com/example/genai_chatbot/android/MainActivity.kt
package com.example.genai_chatbot.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.genai_chatbot.AndroidFilePicker
import com.example.genai_chatbot.PickAFileUI

class MainActivity : ComponentActivity() {
    private lateinit var filePicker: AndroidFilePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filePicker = AndroidFilePicker(this)
        setContent {
            PickAFileUI(filePicker)
        }
    }
}

