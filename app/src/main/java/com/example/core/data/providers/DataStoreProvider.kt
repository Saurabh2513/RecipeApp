package com.example.core.data.providers

interface DataStoreProvider {

    suspend fun readOnboardingDidShow(): Boolean

    suspend fun writeOnboardingDidShow()
}