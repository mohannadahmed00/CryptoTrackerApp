package com.giraffe.cryptotrackerapp.presentation.coins_list

import androidx.compose.runtime.Immutable
import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.domain.entities.PriceEntity
import com.giraffe.cryptotrackerapp.presentation.models.CoinUi

@Immutable
data class CoinsListScreenState(
    val isLoading: Boolean = false,
    val coinsList: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
    val networkError: NetworkError? = null,
    val priceHistory: List<PriceEntity> = emptyList()
)
