package com.giraffe.cryptotrackerapp.presentation.ui_models

import androidx.annotation.DrawableRes
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.getDrawableIdForCoin
import com.giraffe.cryptotrackerapp.core.toDisplayableNumber
import com.giraffe.cryptotrackerapp.domain.entities.CoinEntity
import com.giraffe.cryptotrackerapp.presentation.components.chart.DataPoint

data class CoinUi(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val priceUsd: DisplayableNumber,
    val marketCapUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val priceHistory: List<DataPoint> = emptyList()
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


