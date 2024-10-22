package com.giraffe.cryptotrackerapp.presentation.coins_list

import com.giraffe.cryptotrackerapp.presentation.ui_models.CoinUi

sealed interface CoinsListScreenActions {
    data class OnCoinClick(val coin: CoinUi) : CoinsListScreenActions
    data object OnRefresh : CoinsListScreenActions
}