package com.giraffe.cryptotrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.giraffe.cryptotrackerapp.core.navigation.AdaptiveCoinListDetailPane
import com.giraffe.cryptotrackerapp.presentation.theme.CryptoTrackerAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CryptoTrackerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AdaptiveCoinListDetailPane()
                }
            }
        }
    }
}