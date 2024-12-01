package com.example.features.categories.domain.model

data class Category(
    val name: String,
    val items: List<CategoryItem>
)