package com.giraffe.cryptotrackerapp.domain.entities

data class CoinEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val priceUsd: Double,
    val marketCapUsd: Double,
    val changePercent24Hr: Double,
)
