package com.giraffe.cryptotrackerapp.crypto.data.datasources.remote

import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.core.domain.util.Resource
import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.responses.CoinResponse

interface RemoteDataSource {
    suspend fun getCoins(): Resource<List<CoinResponse>, NetworkError>
}