package com.example.bilemonline.data.model

data class ChangePasswordResponse(
    val affected: Int,
    val generatedMaps: List<Any>,
    val raw: List<Any>
)