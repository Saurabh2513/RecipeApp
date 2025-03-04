package com.example.core.domain.repository

import com.example.core.data.database.dao.RecipeDao
import com.example.core.data.network.model.asDomainModel
import com.fabirt.roka.core.data.database.entities.DatabaseIngredient
import com.fabirt.roka.core.data.database.entities.DatabaseInstruction
import com.fabirt.roka.core.data.database.entities.DatabaseRecipe
import com.fabirt.roka.core.data.database.entities.asDomainModel
import com.example.core.data.network.services.RecipeService
import com.example.core.domain.model.Recipe
import com.example.core.domain.model.toDatabaseModel
import com.example.core.error.Failure
import com.fabirt.roka.core.error.toFailure
import com.example.core.utils.Either
import com.example.core.utils.left
import com.example.core.utils.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(
    private val service: RecipeService,
    private val recipeDao: RecipeDao
) : RecipeRepository {

    override suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): Either<Failure, List<Recipe>> {
        return try {
            val result = service.searchRecipes(query, addRecipeInformation, number, offset)
            val recipes = result.results.map { it.asDomainModel() }
            right(recipes)
        } catch (e: Exception) {
            left(Failure.UnexpectedFailure)
        }
    }

    override suspend fun searchRecipes(
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): Either<Failure, List<Recipe>> {
        return try {
            val result = service.searchRecipes(addRecipeInformation, number, offset, options)
            val recipes = result.results.map { it.asDomainModel() }
            right(recipes)
        } catch (e: Exception) {
            left(Failure.UnexpectedFailure)
        }
    }

    override suspend fun requestRecipeInformation(
        id: Int
    ): Either<Failure, Recipe> {
        return try {
            val response = service.requestRecipeInformation(id)
            right(response.asDomainModel())
        } catch (e: Throwable) {
            left(e.toFailure())
        }
    }

    override fun requestFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipesWithInformation().map { dbRecipe ->
            dbRecipe.map { it.asDomainModel() }
        }
    }

    override suspend fun saveFavoriteRecipe(recipe: Recipe) {
        val model = recipe.toDatabaseModel()
        recipeDao.insertRecipe(
            recipe = model.recipe,
            ingredients = model.ingredients,
            instructions = model.instructions
        )
    }

    override fun requestFavoriteRecipeById(id: Int): Flow<Recipe?> {
        return recipeDao.getRecipeById(id).map { it?.asDomainModel() }
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        val model = recipe.toDatabaseModel()
        recipeDao.deleteRecipe(
            recipe = model.recipe,
            ingredients = model.ingredients,
            instructions = model.instructions
        )
    }

    override suspend fun deleteMultipleFavorites(recipes: List<Recipe>) {
        val dbRecipes = mutableListOf<DatabaseRecipe>()
        val dbIngredients = mutableListOf<DatabaseIngredient>()
        val dbInstructions = mutableListOf<DatabaseInstruction>()

        for (recipe in recipes) {
            val model = recipe.toDatabaseModel()
            dbRecipes.add(model.recipe)
            dbIngredients.addAll(model.ingredients)
            dbInstructions.addAll(model.instructions)
        }

        recipeDao.deleteMultipleRecipes(dbRecipes, dbIngredients, dbInstructions)
    }
}