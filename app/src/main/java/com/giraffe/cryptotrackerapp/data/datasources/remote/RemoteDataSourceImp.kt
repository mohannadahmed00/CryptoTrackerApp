package com.giraffe.cryptotrackerapp.data.datasources.remote

import com.giraffe.cryptotrackerapp.core.utils.data_util.networking.constructUrl
import com.giraffe.cryptotrackerapp.core.utils.data_util.networking.safeCall
import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import com.giraffe.cryptotrackerapp.core.utils.domain_util.map
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.CoinResponse
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.CoinsResponse
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.PriceHistoryResponse
import com.giraffe.cryptotrackerapp.data.datasources.remote.responses.PriceResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteDataSourceImp(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getCoins(): Resource<List<CoinResponse>, NetworkError> {
        return safeCall<CoinsResponse> { httpClient.get(urlString = constructUrl("/assets")) }.map { it.data }
    }

    override suspend fun getCoinPriceHistory(id: String): Resource<List<PriceResponse>, NetworkError> {
        return safeCall<PriceHistoryResponse> {
            httpClient.get(urlString = constructUrl("/assets/$id/history"))
            {
                parameter("interval", "h6")
            }
        }.map { it.data }
    }
}