package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError

sealed interface CoinsListScreenEvents {
    data class Error(val error: NetworkError) : CoinsListScreenEvents
}