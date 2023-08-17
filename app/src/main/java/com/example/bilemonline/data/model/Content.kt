package com.example.bilemonline.data.model

data class Content(
    val content: List<ContentX>,
    val description: String,
    val id: String,
    val isPassed: Boolean,
    val theme: String
)