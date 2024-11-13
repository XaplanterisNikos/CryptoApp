package com.foxnks.cryptoapp.model

data class CryptoModel(
    val id: String,
    val symbol: String,
    val current_price: Double,
    val high_24h: Double,
)