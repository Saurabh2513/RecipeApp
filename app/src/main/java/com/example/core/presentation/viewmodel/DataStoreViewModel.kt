package com.example.core.presentation.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.providers.DataStoreProvider
import kotlinx.coroutines.launch

class DataStoreViewModel @ViewModelInject constructor(
    private val dataStoreProvider: DataStoreProvider
) : ViewModel() {

    suspend fun readOnboardingDidShow() = dataStoreProvider.readOnboardingDidShow()

    fun writeOnboardingDidShow() {
        viewModelScope.launch {
            dataStoreProvider.writeOnboardingDidShow()
        }
    }
}