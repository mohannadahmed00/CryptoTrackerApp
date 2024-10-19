package com.giraffe.cryptotrackerapp.core.domain.util

typealias DomainError = Error

sealed interface Resource<out D, out E : Error> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Error<out E : DomainError>(val error: E) : Resource<Nothing, E>
}


inline fun <T, E : Error, R> Resource<T, E>.map(map: (T) -> R): Resource<R, E> {
    return when (this) {
        is Resource.Error -> Resource.Error(error)
        is Resource.Success -> Resource.Success(map(data))
    }
}

inline fun <T, E : Error> Resource<T, E>.onSuccess(action: (T) -> Unit): Resource<T, E> {
    return when (this) {
        is Resource.Error -> this
        is Resource.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E : Error> Resource<T, E>.onError(action: (E) -> Unit): Resource<T, E> {
    return when (this) {
        is Resource.Error -> {
            action(error)
            this
        }

        is Resource.Success -> this
    }
}