package com.example.bilemonline.data.model

data class PassingCoursesItem(
    val authors: List<AuthorXX>,
    val description: String,
    val id: String,
    val isFree: Boolean,
    val logo: Logo?,
    val price: Int,
    val rating: Int,
    val status: String,
    val students: Int,
    val title: String,
    val wallpaper: Any
)