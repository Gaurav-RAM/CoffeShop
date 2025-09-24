package com.example.coffeshop.Domain

data class CategoryModel(
    val title: String = "",
    val id: Int = 0,
    val url: String = ""   // <-- added to match Firebase
)
