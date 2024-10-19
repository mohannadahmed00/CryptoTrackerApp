package com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponse(
    val data : List<CoinResponse>
)
