package com.example.core.presentation.dispatchers

import android.view.View
import com.example.core.domain.model.Recipe

interface RecipeEventDispatcher {
    fun onRecipePressed(recipe: Recipe, view: View)
}