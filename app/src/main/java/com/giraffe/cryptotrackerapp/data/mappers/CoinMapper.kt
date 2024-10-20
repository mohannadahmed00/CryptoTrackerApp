package com.giraffe.cryptotrackerapp.data.mappers

import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.CoinResponse
import com.giraffe.cryptotrackerapp.domain.entities.CoinEntity

fun CoinResponse.toEntity() = CoinEntity(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    priceUsd = priceUsd,
    marketCapUsd = marketCapUsd,
    changePercent24Hr = changePercent24Hr
)