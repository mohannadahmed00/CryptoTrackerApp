package com.giraffe.cryptotrackerapp.presentation.coins_list

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError

sealed interface CoinsListScreenEvents {
    data class Error(val error: NetworkError) : CoinsListScreenEvents
}