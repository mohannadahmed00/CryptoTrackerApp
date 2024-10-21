package com.giraffe.cryptotrackerapp.domain.usecases

import com.giraffe.cryptotrackerapp.domain.repository.Repository

class GetCoinPriceHistoryUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: String) = repository.getCoinPriceHistory(id)
}