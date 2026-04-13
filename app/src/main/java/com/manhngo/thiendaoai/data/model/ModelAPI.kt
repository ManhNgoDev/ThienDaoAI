package com.manhngo.thiendaoai.data.model

data class ChatRequest(
    val model: String = "google/gemma-4-e4b",
    val messages: List<ApiMessage>,
    val temperature: Double = 0.4
)

data class ApiMessage(
    val role: String,
    val content: String?,
    val reasoning_content: String? = null
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: ApiMessage
)


