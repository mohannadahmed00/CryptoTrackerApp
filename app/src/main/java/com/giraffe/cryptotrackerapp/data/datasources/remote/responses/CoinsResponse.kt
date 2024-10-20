package com.giraffe.cryptotrackerapp.data.datasources.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponse(
    val data: List<CoinResponse>
)
