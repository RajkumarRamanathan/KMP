package com.example.genai_chatbot

import platform.Foundation.NSURL
import platform.UIKit.UIDocumentPickerDelegateProtocol
import platform.UIKit.UIDocumentPickerMode
import platform.UIKit.UIDocumentPickerViewController
import platform.UIKit.UIViewController
import platform.darwin.NSObject

var fileName: String = ""

class IOSFilePicker(
    private val viewController: UIViewController?
) : FilePicker {
    override fun pickFile() {
        val documentPicker = UIDocumentPickerViewController(
            documentTypes = listOf("com.adobe.pdf"),
            inMode = UIDocumentPickerMode.UIDocumentPickerModeImport
        )
        documentPicker.delegate = object : NSObject(), UIDocumentPickerDelegateProtocol {
            override fun documentPicker(
                controller: UIDocumentPickerViewController,
                didPickDocumentAtURL: NSURL
            ) {
                fileName = didPickDocumentAtURL.absoluteString ?: ""
                _delegate?.updateUI("File selected: $fileName")
            }
        }
        //= DocumentPickerDelegate(_delegate)
        viewController?.presentViewController(documentPicker, animated = true, completion = null)
    }
    override var _delegate: UIUpdateCallback? = null
}