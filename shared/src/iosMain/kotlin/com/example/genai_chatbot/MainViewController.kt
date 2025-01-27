package com.example.genai_chatbot

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        val filePicker: FilePicker = IOSFilePicker(UIViewController())
        App(filePicker)
    }
}