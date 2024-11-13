package com.foxnks.cryptoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.foxnks.cryptoapp.model.CryptoModel

import com.foxnks.cryptoapp.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {

    private val _cryptoData = MutableLiveData<List<CryptoModel>?>()
    val cryptoData: LiveData<List<CryptoModel>?> get() = _cryptoData

    // Holds the current currency symbol
    private val _currencySymbol = MutableLiveData<String>("€")
    val currencySymbol: LiveData<String> get() = _currencySymbol

    fun getCryptoDataEUR() {
        _currencySymbol.value = "€"
        fetchCryptoData("eur")
    }

    fun getCryptoDataUSD() {
        _currencySymbol.value = "$"
        fetchCryptoData("usd")
    }

    private fun fetchCryptoData(currency: String) {
        viewModelScope.launch {
            val response = repository.getCryptoData(currency)
            _cryptoData.value = if (response.isSuccessful) response.body() else null
        }
    }
}