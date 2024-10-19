package com.giraffe.cryptotrackerapp.core

import com.giraffe.cryptotrackerapp.crypto.presentation.models.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}