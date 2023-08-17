package com.example.bilemonline.data.model

import java.io.Serializable

data class Section(
    val description: String,
    val id: String,
    val isPassed: Boolean,
    val theme: String
): Serializable