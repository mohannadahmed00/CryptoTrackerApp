package com.giraffe.cryptotrackerapp.domain.usecases

import com.giraffe.cryptotrackerapp.domain.repository.Repository

class FetchCoinsUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCoins()
}