package com.giraffe.cryptotrackerapp.data.datasources.remote

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.CoinResponse
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.PriceResponse

interface RemoteDataSource {
    suspend fun getCoins(): Resource<List<CoinResponse>, NetworkError>
    suspend fun getCoinPriceHistory(id:String): Resource<List<PriceResponse>, NetworkError>
}