package com.example.genai_chatbot

import KoinInitializer
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

lateinit var filePicker: FilePicker
fun MainViewController(): UIViewController {
    val composeViewController = ComposeUIViewController(
        configure = {
            KoinInitializer().init()
        }
    ) {
        PickAFileUI(filePicker)

    }
    filePicker = IOSFilePicker(composeViewController)
    return composeViewController
}
