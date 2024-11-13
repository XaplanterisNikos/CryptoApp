package com.foxnks.cryptoapp.repository



import com.foxnks.cryptoapp.service.CryptoApiService


class CryptoRepository(private val apiService: CryptoApiService) {
    suspend fun getCryptoData(currency: String) = apiService.getCryptoData(currency)
}