package com.example.features.categories.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.*
import com.example.core.constants.K
import com.example.core.data.network.services.RecipeService
import com.example.core.domain.model.Recipe
import com.example.features.categories.domain.model.CategoryItem
import com.example.features.categories.domain.repository.FilteredRecipesPagingSource
import kotlinx.coroutines.flow.Flow

class CategoryDetailViewModel @ViewModelInject constructor(
    private val service: RecipeService
) : ViewModel() {

    var recipesFlow: Flow<PagingData<Recipe>>? = null

    fun requestRecipesForCategory(categoryItem: CategoryItem) {
        recipesFlow = Pager(PagingConfig(K.RECIPES_PER_PAGE)) {
            val options = mapOf(categoryItem.type to categoryItem.name)
            FilteredRecipesPagingSource(service, options)
        }.flow
            .cachedIn(viewModelScope)
    }
}