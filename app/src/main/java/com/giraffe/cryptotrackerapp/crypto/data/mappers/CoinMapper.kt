package com.giraffe.cryptotrackerapp.crypto.data.mappers

import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.responses.CoinResponse
import com.giraffe.cryptotrackerapp.crypto.domain.entities.CoinEntity

fun CoinResponse.toEntity() = CoinEntity(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    priceUsd = priceUsd,
    marketCapUsd = marketCapUsd,
    changePercent24Hr = changePercent24Hr
)