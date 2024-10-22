package com.giraffe.cryptotrackerapp.presentation.viewmodel

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError

sealed interface CoinsSharedEvents {
    data class Error(val error: NetworkError) : CoinsSharedEvents
}