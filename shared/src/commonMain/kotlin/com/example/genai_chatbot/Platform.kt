package com.example.genai_chatbot

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform