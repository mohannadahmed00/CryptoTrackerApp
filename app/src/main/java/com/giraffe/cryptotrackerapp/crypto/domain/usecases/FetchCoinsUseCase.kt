package com.giraffe.cryptotrackerapp.crypto.domain.usecases

import com.giraffe.cryptotrackerapp.crypto.domain.repository.Repository

class FetchCoinsUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getCoins()
}