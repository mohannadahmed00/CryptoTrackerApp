package com.giraffe.cryptotrackerapp.di

import com.giraffe.cryptotrackerapp.core.data.networking.HttpClientFactory
import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.RemoteDataSource
import com.giraffe.cryptotrackerapp.crypto.data.datasources.remote.RemoteDataSourceImp
import com.giraffe.cryptotrackerapp.crypto.data.repository.RepositoryImp
import com.giraffe.cryptotrackerapp.crypto.domain.repository.Repository
import com.giraffe.cryptotrackerapp.crypto.domain.usecases.FetchCoinsUseCase
import com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.CoinsListScreenVM
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteDataSourceImp).bind<RemoteDataSource>()
    singleOf(::RepositoryImp).bind<Repository>()
    singleOf(::FetchCoinsUseCase)
    viewModelOf(::CoinsListScreenVM)
}