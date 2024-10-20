package com.giraffe.cryptotrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.giraffe.cryptotrackerapp.presentation.coin_details.CoinDetailsScreen
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreen
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenVM
import com.giraffe.cryptotrackerapp.presentation.theme.CryptoTrackerAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CryptoTrackerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val viewModel = koinViewModel<CoinsListScreenVM>()
                    val state by viewModel.state.collectAsState()
                    if (state.selectedCoin !=null){
                        CoinDetailsScreen()
                    }else {
                        CoinsListScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}