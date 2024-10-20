package com.giraffe.cryptotrackerapp.crypto.domain.repository

import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.core.domain.util.Resource
import com.giraffe.cryptotrackerapp.crypto.domain.entities.CoinEntity

interface Repository {
    suspend fun getCoins(): Resource<List<CoinEntity>, NetworkError>
}