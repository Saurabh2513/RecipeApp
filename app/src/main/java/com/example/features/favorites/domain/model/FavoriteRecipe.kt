package com.example.features.favorites.domain.model

import com.example.core.domain.model.Recipe

data class FavoriteRecipe(
    val data: Recipe,
    val isSelected: Boolean
) {
    val id: Int get() = data.id
}