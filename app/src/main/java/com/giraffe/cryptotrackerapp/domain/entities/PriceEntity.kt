package com.giraffe.cryptotrackerapp.domain.entities

import java.time.ZonedDateTime

data class PriceEntity(
    val priceUsd: String,
    val dateTime: ZonedDateTime
)