package com.giraffe.cryptotrackerapp.core.data.networking

import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError
import com.giraffe.cryptotrackerapp.core.domain.util.Resource
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResource(response: HttpResponse): Resource<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Resource.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Resource.Error(NetworkError.SERIALIZATION)
            }
        }

        408 -> Resource.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Resource.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Resource.Error(NetworkError.SERVER_ERROR)
        else -> Resource.Error(NetworkError.UNKNOWN)
    }
}