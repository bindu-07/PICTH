package com.photo.picth.ui.presentation.homepage

data class ApiResponse(
    val categories: List<Category>
)

data class Category(
    val name: String,
    val items: List<Item>
)

data class Item(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String
)
