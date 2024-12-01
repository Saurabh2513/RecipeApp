package com.example.di

import android.content.Context
import com.example.core.data.database.AppDatabase
import com.example.core.data.network.client.RecipesApiClient
import com.example.core.data.network.services.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeService(): RecipeService = RecipesApiClient.createRecipeService()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase.createDatabase(context)

    @Provides
    @Singleton
    fun provideRecipeDao(database: AppDatabase) = database.recipeDao()
}