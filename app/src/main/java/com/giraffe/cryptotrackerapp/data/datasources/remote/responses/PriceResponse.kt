package com.giraffe.cryptotrackerapp.data.datasources.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class PriceResponse(
    val priceUsd: String,
    val time: Long
)