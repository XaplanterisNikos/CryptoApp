package com.foxnks.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foxnks.cryptoapp.repository.CryptoRepository

class CryptoViewModelFactory(
    private val repository: CryptoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CryptoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}