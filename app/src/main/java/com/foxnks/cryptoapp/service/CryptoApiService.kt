package com.foxnks.cryptoapp.service

import com.foxnks.cryptoapp.model.CryptoHistoricalData
import com.foxnks.cryptoapp.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CryptoApiService {

    @GET("coins/markets")
    suspend fun getCryptoData(
        @Query("vs_currency") currency: String
    ): Response<List<CryptoModel>>


    // New service method for historical prices over the last 7 days
    @GET("coins/{id}/market_chart")
    suspend fun getCryptoHistoricalData(
        @Path("id") cryptoId: String,
        @Query("vs_currency") currency: String,
        @Query("days") days: Int = 7,
        @Query("interval") interval: String = "daily"
    ): Response<CryptoHistoricalData>

}