package com.giraffe.cryptotrackerapp.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.giraffe.cryptotrackerapp.core.presentation.util.getDrawableIdForCoin
import com.giraffe.cryptotrackerapp.core.toDisplayableNumber
import com.giraffe.cryptotrackerapp.crypto.domain.entities.CoinEntity

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val priceUsd: DisplayableNumber,
    val marketCapUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun CoinEntity.toCoinUi() = CoinUi(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    priceUsd = priceUsd.toDisplayableNumber(),
    marketCapUsd = marketCapUsd.toDisplayableNumber(),
    changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
    iconRes = getDrawableIdForCoin(symbol)
)


