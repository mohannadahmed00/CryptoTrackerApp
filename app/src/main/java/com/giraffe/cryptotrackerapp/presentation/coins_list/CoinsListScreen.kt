package com.giraffe.cryptotrackerapp.presentation.coins_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.ObserveAsEvents
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.showToast
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.toString
import com.giraffe.cryptotrackerapp.presentation.components.CoinItem
import com.giraffe.cryptotrackerapp.presentation.components.previewCoinUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.androidx.compose.koinViewModel

//viewModel: CoinsListScreenVM =  viewModel()
@Composable
fun CoinsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinsListScreenVM = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    CoinsListContent(
        modifier = modifier, state = state, events = viewModel.events, actions = viewModel::onAction
    )
}

@Composable
fun CoinsListContent(
    modifier: Modifier = Modifier,
    state: CoinsListScreenState = previewState,
    events: Flow<CoinsListScreenEvents> = emptyFlow(),
    actions: (action: CoinsListScreenActions) -> Unit
) {
    val context = LocalContext.current
    ObserveAsEvents(events) {
        when (it) {
            is CoinsListScreenEvents.Error -> it.error.showToast(context)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.networkError != null) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.networkError.toString(context), textAlign = TextAlign.Center)
                HorizontalDivider(thickness = 38.dp, color = Color.Transparent)
                Button(
                    onClick = { actions(CoinsListScreenActions.OnRefresh) }
                ) { Text("Refresh") }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.coinsList, key = { it.id }) {
                    CoinItem(it){
                        actions(CoinsListScreenActions.OnCoinClick(it))
                    }
                    HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer)
                }
            }
        }
    }
}

internal val previewState = CoinsListScreenState(
    isLoading = true,
    coinsList = (1..50).map { previewCoinUi },
    selectedCoin = previewCoinUi
)

@Preview(showBackground = true)
@Composable
fun CoinsListPreview() {
    //CoinsListContent()
}