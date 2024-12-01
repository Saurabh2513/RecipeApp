package com.example.core.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.fabirt.roka.MainGraphDirections
import com.example.core.domain.model.Recipe
import com.example.recipeapp.R
import com.example.features.detail.presentation.viewmodel.RecipeDetailViewModel

fun Fragment.findMainNavController(): NavController {
    return Navigation.findNavController(
        requireActivity(),
        R.id.mainNavHostFragment
    )
}

fun Fragment.navigateToRecipeDetail(
    recipe: Recipe,
    view: View,
    isFavorite: Boolean = false
    ) {
    val viewModel by activityViewModels<RecipeDetailViewModel>()
    val extras = FragmentNavigatorExtras(view to recipe.id.toString())
    if (isFavorite) {
        viewModel.presentRecipeInfo(recipe)
    } else {
        viewModel.requestRecipeInfo(recipe)
    }
    val action = MainGraphDirections.actionGlobalRecipeDetailFragment(recipe.id.toString(), null)
    findMainNavController().navigate(action, extras)
}