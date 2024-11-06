package com.foxnks.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.foxnks.cryptoapp.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {
    val cryptoData = liveData(Dispatchers.IO) {
        val response = repository.getCryptoData()
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            emit(null)
        }
    }
}