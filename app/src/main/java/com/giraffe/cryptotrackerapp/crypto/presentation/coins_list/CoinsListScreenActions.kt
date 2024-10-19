package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import com.giraffe.cryptotrackerapp.crypto.presentation.models.CoinUi

sealed interface CoinsListScreenActions {
    data class OnCoinClick(val coin: CoinUi) : CoinsListScreenActions
}