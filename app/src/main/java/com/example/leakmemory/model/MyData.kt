package com.example.leakmemory.model

data class MyData(
    val imageResId: Int,
    val title: String,
    val author: String,
    val chapterCount: Int,
    val genres: List<String>,
)
