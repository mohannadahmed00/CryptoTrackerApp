package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import androidx.compose.runtime.Immutable
import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.crypto.presentation.models.CoinUi

@Immutable
data class CoinsListScreenState(
    val isLoading: Boolean = false,
    val coinsList: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
    val networkError: NetworkError? = null
)
