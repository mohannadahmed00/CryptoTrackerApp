package com.giraffe.cryptotrackerapp.data.mappers

import android.annotation.SuppressLint
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.PriceResponse
import com.giraffe.cryptotrackerapp.domain.entities.PriceEntity
import java.time.Instant
import java.time.ZoneId

@SuppressLint("NewApi")
fun PriceResponse.toEntity() = PriceEntity(
    priceUsd = priceUsd,
    dateTime = Instant
        .ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
)