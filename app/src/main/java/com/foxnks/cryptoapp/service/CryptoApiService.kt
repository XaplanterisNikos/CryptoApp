package com.foxnks.cryptoapp.service

import com.foxnks.cryptoapp.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoApiService {

    @GET("coins/markets")
    suspend fun getCryptoData(
        @Query("vs_currency") currency: String
    ): Response<List<CryptoModel>>
}