package com.example.di

import com.example.core.data.providers.DataStoreProvider
import com.example.core.data.providers.DataStoreProviderImpl
import com.example.core.domain.repository.RecipeRepository
import com.example.core.domain.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager

@Module
@InstallIn(ApplicationComponentManager::class)
abstract class BinderModule {

    @Binds
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    abstract fun bindDataStoreProvider(
        dataStoreProviderImpl: DataStoreProviderImpl
    ): DataStoreProvider
}