package com.example.bilemonline.data.model

data class GetSectionResponse(
    val id: String,
    val isPassed: Boolean,
    val order: Int,
    val sections: List<Section>,
    val theme: String
)