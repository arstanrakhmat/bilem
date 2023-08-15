package com.example.bilemonline.data.model

data class GetModuleResponseItem(
    val id: String,
    val isPassed: Boolean,
    val order: Int,
    val sections: Any,
    val theme: String
)