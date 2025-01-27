package com.example.genai_chatbot

// shared/src/commonMain/kotlin/com/example/genai_chatbot/App.kt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




interface FilePicker {
    fun pickFile()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(filePicker: FilePicker) {
    val selectedFileUri by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PDF ChatBot") }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                Button(
                    onClick = {
                        filePicker.pickFile()
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Select PDF")
                }
                selectedFileUri?.let { uri ->
                    Text("Selected file: $uri", modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = { /* Implement upload file for iOS */ },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Upload PDF File")
                    }
                }
            }
        }
    )
}
//
//fun uploadFile(context: Context, fileUri: Uri) {
//    val file = File(fileUri.path!!)
//    val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
//    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://your.api.url/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service = retrofit.create(FileUploadService::class.java)
//    val call = service.uploadFile(body)
//
//    call.enqueue(object : Callback<Void> {
//        override fun onResponse(call: Call<Void>, response: Response<Void>) {
//            if (response.isSuccessful) {
//                // Handle successful upload
//            } else {
//                // Handle upload failure
//            }
//        }
//
//        override fun onFailure(call: Call<Void>, t: Throwable) {
//            // Handle upload error
//        }
//    })
//}