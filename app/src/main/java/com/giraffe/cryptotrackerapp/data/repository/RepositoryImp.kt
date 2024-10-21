package com.giraffe.cryptotrackerapp.data.repository

import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import com.giraffe.cryptotrackerapp.core.utils.domain_util.map
import com.giraffe.cryptotrackerapp.data.datasources.remote.RemoteDataSource
import com.giraffe.cryptotrackerapp.data.mappers.toEntity
import com.giraffe.cryptotrackerapp.domain.entities.CoinEntity
import com.giraffe.cryptotrackerapp.domain.entities.PriceEntity
import com.giraffe.cryptotrackerapp.domain.repository.Repository

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCoins(): Resource<List<CoinEntity>, NetworkError> {
        return remoteDataSource.getCoins().map { it.map { coin -> coin.toEntity() } }
    }

    override suspend fun getCoinPriceHistory(id: String): Resource<List<PriceEntity>, NetworkError> {
        return remoteDataSource.getCoinPriceHistory(id).map { it.map { price -> price.toEntity() } }
    }
}