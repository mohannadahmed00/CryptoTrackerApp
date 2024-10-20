package com.giraffe.cryptotrackerapp.domain.repository

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import com.giraffe.cryptotrackerapp.domain.entities.CoinEntity

interface Repository {
    suspend fun getCoins(): Resource<List<CoinEntity>, NetworkError>
}