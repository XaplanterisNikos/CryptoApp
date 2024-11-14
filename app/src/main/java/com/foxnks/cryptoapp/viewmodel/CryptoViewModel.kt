// CryptoViewModel.kt
package com.foxnks.cryptoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foxnks.cryptoapp.model.CryptoHistoricalData
import com.foxnks.cryptoapp.model.CryptoModel
import com.foxnks.cryptoapp.repository.CryptoRepository
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {

    val cryptoData = MutableLiveData<List<CryptoModel>>()
    val historicalData = MutableLiveData<CryptoHistoricalData>()  // Changed to match adapter
    val currencySymbol = MutableLiveData<String>()

    // Method to fetch crypto data in EUR
    fun getCryptoDataEUR() {
        viewModelScope.launch {
            val response = repository.getCryptoData("eur")
            if (response.isSuccessful) {
                cryptoData.postValue(response.body())
                currencySymbol.postValue("€")
            }
        }
    }

    // Method to fetch crypto data in USD
    fun getCryptoDataUSD() {
        viewModelScope.launch {
            val response = repository.getCryptoData("usd")
            if (response.isSuccessful) {
                cryptoData.postValue(response.body())
                currencySymbol.postValue("$")
            }
        }
    }

    // Method to fetch historical data for a specific cryptocurrency
    fun getHistoricalPrices(cryptoId: String) {
        viewModelScope.launch {
            val response = repository.getHistoricalData(cryptoId, "usd")
            if (response.isSuccessful) {
                historicalData.postValue(response.body())  // Use CryptoHistoricalData directly
            }
        }
    }
}