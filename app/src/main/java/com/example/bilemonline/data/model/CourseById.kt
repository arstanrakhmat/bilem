package com.example.bilemonline.data.model

data class CourseById(
    val authors: List<AuthorX>,
    val categoryId: String,
    val createdAt: String,
    val deleted: Boolean,
    val description: String,
    val id: String,
    val isFree: Boolean,
    val logo: Logo,
    val price: Int,
    val rating: Int,
    val status: String,
    val title: String,
    val updatedAt: String,
    val wallpaper: Wallpaper
) : java.io.Serializable