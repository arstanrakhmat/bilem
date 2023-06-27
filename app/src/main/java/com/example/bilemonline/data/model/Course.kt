package com.example.bilemonline.data.model

import android.graphics.Bitmap

data class Course(
    val title: String,
    val author: String,
    val image: Int,
    val rating: Double,
    val people_bought: Int,
    val price: Int
)
