package com.giraffe.cryptotrackerapp.data.datasources.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class PriceHistoryResponse(
    val data: List<PriceResponse>
)