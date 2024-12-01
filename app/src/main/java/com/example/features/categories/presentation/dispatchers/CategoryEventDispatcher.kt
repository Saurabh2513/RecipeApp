package com.example.features.categories.presentation.dispatchers

import android.view.View
import com.example.features.categories.domain.model.CategoryItem

interface CategoryEventDispatcher {
    fun onCategoryPressed(category: CategoryItem, view: View)
}