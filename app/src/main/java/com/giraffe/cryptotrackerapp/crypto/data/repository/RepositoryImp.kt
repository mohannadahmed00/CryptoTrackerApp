package com.giraffe.cryptotrackerapp.crypto.data.repository

import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.core.domain.util.Resource
import com.giraffe.cryptotrackerapp.core.domain.util.map
import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.RemoteDataSource
import com.giraffe.cryptotrackerapp.crypto.data.mappers.toEntity
import com.giraffe.cryptotrackerapp.crypto.domain.entities.CoinEntity
import com.giraffe.cryptotrackerapp.crypto.domain.repository.Repository

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCoins(): Resource<List<CoinEntity>, NetworkError> {
        return remoteDataSource.getCoins().map { it.map { coin -> coin.toEntity() } }
    }
}