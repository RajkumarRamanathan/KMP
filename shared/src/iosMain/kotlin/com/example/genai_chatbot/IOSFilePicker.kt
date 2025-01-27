package com.example.genai_chatbot

import kotlinx.cinterop.ExportObjCClass
import platform.Foundation.NSURL
import platform.UIKit.UIDocumentPickerDelegateProtocol
import platform.UIKit.UIDocumentPickerMode
import platform.UIKit.UIDocumentPickerViewController
import platform.UIKit.UIViewController
import platform.darwin.NSObject

class IOSFilePicker(private val viewController: UIViewController) : FilePicker {
    override fun pickFile() {
        val documentPicker = UIDocumentPickerViewController(documentTypes = listOf("com.adobe.pdf"), inMode = UIDocumentPickerMode.UIDocumentPickerModeImport)
        documentPicker.delegate = DocumentPickerDelegate()
        viewController.presentViewController(documentPicker, animated = true, completion = null)
    }
}

@ExportObjCClass
class DocumentPickerDelegate : NSObject(), UIDocumentPickerDelegateProtocol {
    override fun documentPicker(controller: UIDocumentPickerViewController, didPickDocumentAtURL: NSURL) {
        // Handle the picked file URL
    }
}