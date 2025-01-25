package com.example.pdfchatbot.android

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
    }

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
                        filePickerLauncher.launch("application/pdf")
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Select PDF")
                }
                selectedFileUri?.let { uri ->
                    Text("Selected file: $uri", modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = { uploadFile(context, uri) },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Upload PDF File")
                    }
                }
                // Add more UI elements here
            }
        }
    )
}
fun uploadFile(context: Context, fileUri: Uri) {
    val file = File(fileUri.path!!)
    val requestFile = file.asRequestBody("application/pdf".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

    val retrofit = Retrofit.Builder()
        .baseUrl("https://your.api.url/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(FileUploadService::class.java)
    val call = service.uploadFile(body)

    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                // Handle successful upload
            } else {
                // Handle upload failure
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            // Handle upload error
        }
    })
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
