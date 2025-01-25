package com.example.pdfchatbot.android

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileUploadService {
    @Multipart
    @POST("upload")
    fun uploadFile(@Part file: MultipartBody.Part): Call<Void>
}