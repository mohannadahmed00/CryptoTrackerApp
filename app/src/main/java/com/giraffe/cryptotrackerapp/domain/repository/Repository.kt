package com.giraffe.cryptotrackerapp.domain.repository

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import com.giraffe.cryptotrackerapp.domain.entities.CoinEntity
import com.giraffe.cryptotrackerapp.domain.entities.PriceEntity

interface Repository {
    suspend fun getCoins(): Resource<List<CoinEntity>, NetworkError>
    suspend fun getCoinPriceHistory(id:String): Resource<List<PriceEntity>, NetworkError>
}