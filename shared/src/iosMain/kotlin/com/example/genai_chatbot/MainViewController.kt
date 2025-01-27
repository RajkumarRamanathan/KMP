package com.example.genai_chatbot

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

lateinit var filePicker: FilePicker
fun MainViewController(): UIViewController {
    val composeViewController = ComposeUIViewController {
        App(filePicker)
    }
    filePicker = IOSFilePicker(composeViewController)
    return composeViewController
}
