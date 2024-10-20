package com.giraffe.cryptotrackerapp.core.utils.data_util.networking

import com.giraffe.cryptotrackerapp.BuildConfig
import com.giraffe.cryptotrackerapp.core.utils.domain_util.NetworkError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.Resource
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

object NetworkHelper {
    fun constructUrl(url: String): String {
        return when {
            url.contains(BuildConfig.BASE_URL) -> url
            url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
            else -> BuildConfig.BASE_URL + url
        }
    }

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

    suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Resource<T, NetworkError> {
        val response = try {
            execute()
        } catch (e: UnresolvedAddressException) {
            return Resource.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Resource.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Resource.Error(NetworkError.UNKNOWN)
        }
        return com.giraffe.cryptotrackerapp.core.utils.data_util.networking.responseToResource(
            response
        )
    }
}