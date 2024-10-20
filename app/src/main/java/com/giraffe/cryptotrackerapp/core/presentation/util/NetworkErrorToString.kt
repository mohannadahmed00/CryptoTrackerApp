package com.giraffe.cryptotrackerapp.core.presentation.util

import android.content.Context
import android.widget.Toast
import com.giraffe.cryptotrackerapp.R
import com.giraffe.cryptotrackerapp.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val stringResId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.REQUEST_TIMEOUT
        NetworkError.TOO_MANY_REQUESTS -> R.string.TOO_MANY_REQUESTS
        NetworkError.NO_INTERNET -> R.string.NO_INTERNET
        NetworkError.SERVER_ERROR -> R.string.UNKNOWN
        NetworkError.SERIALIZATION -> R.string.SERIALIZATION
        NetworkError.UNKNOWN -> R.string.UNKNOWN
    }
    return context.getString(stringResId)
}

fun NetworkError.showToast(context: Context) {
    Toast.makeText(context, this.toString(context), Toast.LENGTH_SHORT).show()
}
