package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.components.CoinItem
import com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.components.previewCoinUi
import org.koin.androidx.compose.koinViewModel

//viewModel: CoinsListScreenVM =  viewModel()
@Composable
fun CoinsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinsListScreenVM = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    CoinsListContent(modifier, state)
}

@Composable
fun CoinsListContent(
    modifier: Modifier = Modifier,
    state: CoinsListScreenState = previewState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.errorMsg != null) {
            Text(text = state.errorMsg, color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.coinsList, key = { it.id }) {
                    CoinItem(it)
                    HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)
                }
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
    CoinsListContent()
}