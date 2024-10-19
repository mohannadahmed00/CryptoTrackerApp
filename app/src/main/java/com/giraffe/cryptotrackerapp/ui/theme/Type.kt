package com.giraffe.cryptotrackerapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.giraffe.cryptotrackerapp.R


val SpaceMono = FontFamily(
    Font(
        resId = R.font.space_mono_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.space_mono_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.space_mono_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.space_mono_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
)
val Baseline = Typography()
val Typography = Typography(
    displayLarge = Baseline.displayLarge.copy(fontFamily = SpaceMono),
    displayMedium = Baseline.displayMedium.copy(fontFamily = SpaceMono),
    displaySmall = Baseline.displaySmall.copy(fontFamily = SpaceMono),
    headlineLarge = Baseline.headlineLarge.copy(fontFamily = SpaceMono),
    headlineMedium = Baseline.headlineMedium.copy(fontFamily = SpaceMono),
    headlineSmall = Baseline.headlineSmall.copy(fontFamily = SpaceMono),
    titleLarge = Baseline.titleLarge.copy(fontFamily = SpaceMono),
    titleMedium = Baseline.titleMedium.copy(fontFamily = SpaceMono),
    titleSmall = Baseline.titleSmall.copy(fontFamily = SpaceMono),
    bodyLarge = Baseline.bodyLarge.copy(fontFamily = SpaceMono),
    bodyMedium = Baseline.bodyMedium.copy(fontFamily = SpaceMono),
    bodySmall = Baseline.bodySmall.copy(fontFamily = SpaceMono),
    labelLarge = Baseline.labelLarge.copy(fontFamily = SpaceMono),
    labelMedium = Baseline.labelMedium.copy(fontFamily = SpaceMono),
    labelSmall = Baseline.labelSmall.copy(fontFamily = SpaceMono),
)

