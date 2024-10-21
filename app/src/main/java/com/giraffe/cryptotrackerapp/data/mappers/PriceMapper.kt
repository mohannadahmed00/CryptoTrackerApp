package com.giraffe.cryptotrackerapp.data.mappers

import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.PriceResponse
import com.giraffe.cryptotrackerapp.domain.entities.PriceEntity

fun PriceResponse.toEntity() = PriceEntity(
    priceUsd = priceUsd,
    time = time
)