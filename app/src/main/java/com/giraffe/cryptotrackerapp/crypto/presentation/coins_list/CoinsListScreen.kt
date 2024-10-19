package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.components.CoinItem
import com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.components.previewCoinUi

@Composable
fun CoinsListScreen(
    //viewModel: CoinsListVM
) {
    //val state by viewModel.state.collectAsState()
    CoinsListContent(previewState)
}

@Composable
fun CoinsListContent(state: CoinsListScreenState = previewState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.coinsList) {
                CoinItem(it)
            }
        }
    }
}

internal val previewState = CoinsListScreenState(
    isLoading = true,
    coinsList = (1..50).map { previewCoinUi },
)

@Preview(showBackground = true)
@Composable
fun CoinsListPreview() {
    CoinsListContent(previewState)
}