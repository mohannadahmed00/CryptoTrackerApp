package com.giraffe.cryptotrackerapp.crypto.data.datasources.remote

import com.giraffe.cryptotrackerapp.core.data.networking.constructUrl
import com.giraffe.cryptotrackerapp.core.data.networking.safeCall
import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.core.domain.util.Resource
import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.responses.CoinResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteDataSourceImp(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getCoins(): Resource<List<CoinResponse>, NetworkError> {
        return safeCall<List<CoinResponse>> { httpClient.get(urlString = constructUrl("/assets")) }
    }
}