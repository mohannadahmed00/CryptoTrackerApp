package com.giraffe.cryptotrackerapp.core.di

import com.giraffe.cryptotrackerapp.core.utils.data_util.networking.HttpClientFactory
import com.giraffe.cryptotrackerapp.data.datasources.remote.RemoteDataSource
import com.giraffe.cryptotrackerapp.data.datasources.remote.RemoteDataSourceImp
import com.giraffe.cryptotrackerapp.data.repository.RepositoryImp
import com.giraffe.cryptotrackerapp.domain.repository.Repository
import com.giraffe.cryptotrackerapp.domain.usecases.FetchCoinsUseCase
import com.giraffe.cryptotrackerapp.domain.usecases.GetCoinPriceHistoryUseCase
import com.giraffe.cryptotrackerapp.presentation.viewmodel.CoinsSharedVM
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
    singleOf(::GetCoinPriceHistoryUseCase)
    viewModelOf(::CoinsSharedVM)
}