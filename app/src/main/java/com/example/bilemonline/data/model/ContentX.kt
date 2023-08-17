package com.example.bilemonline.data.model

data class ContentX(
    val answers: String,
    val content: String,
    val header: String,
    val id: String,
    val isMulti: Boolean,
    val isPassed: Boolean,
    val link: String,
    val name: String,
    val order: Int,
    val questions: List<String>,
    val type: String
)