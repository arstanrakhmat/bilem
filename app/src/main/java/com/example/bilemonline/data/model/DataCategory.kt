package com.example.bilemonline.data.model

import java.io.Serializable

data class DataCategory(
    val coursesQuantity: Int,
    val createdAt: String,
    val deleted: Boolean,
    val description: String,
    val id: String,
    val name: String,
    val updatedAt: String
): Serializable