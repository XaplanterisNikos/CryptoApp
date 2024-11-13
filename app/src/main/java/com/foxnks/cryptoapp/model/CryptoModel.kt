package com.foxnks.cryptoapp.model

data class CryptoModel(
    val id: String,
    val symbol: String,
    val current_price: Double,
    val high_24h: Double,
    val low_24h: Double,
    val image: String,
    val market_cap: Long,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double
)