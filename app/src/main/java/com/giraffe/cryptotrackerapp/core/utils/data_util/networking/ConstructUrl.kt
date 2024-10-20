package com.giraffe.cryptotrackerapp.core.utils.data_util.networking

import com.giraffe.cryptotrackerapp.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}