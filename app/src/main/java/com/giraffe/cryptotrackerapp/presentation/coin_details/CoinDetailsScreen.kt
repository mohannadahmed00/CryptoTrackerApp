package com.giraffe.cryptotrackerapp.presentation.coin_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowOutward
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.cryptotrackerapp.R
import com.giraffe.cryptotrackerapp.core.toDisplayableNumber
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenActions
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenEvents
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenState
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenVM
import com.giraffe.cryptotrackerapp.presentation.coins_list.previewState
import com.giraffe.cryptotrackerapp.presentation.components.InfoCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinsListScreenVM = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    CoinDetailsContent(state = state, events = viewModel.events, actions = viewModel::onAction)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsContent(
    modifier: Modifier = Modifier,
    state: CoinsListScreenState = previewState,
    events: Flow<CoinsListScreenEvents> = emptyFlow(),
    actions: (action: CoinsListScreenActions) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.selectedCoin?.let { coin ->
            Icon(
                imageVector = ImageVector.vectorResource(coin.iconRes),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = coin.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = coin.symbol, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    icon = Icons.Rounded.AccountBalance,
                    title = stringResource(R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                )
                InfoCard(
                    icon = Icons.Rounded.AttachMoney,
                    title = stringResource(R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                )
                val absoluteChangeFormatted =
                    (coin.priceUsd.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()
                val isPositive = absoluteChangeFormatted.value > 0.0
                val contentColor = if (isPositive) {
                    MaterialTheme.colorScheme.onTertiary
                } else {
                    MaterialTheme.colorScheme.onError
                }

                InfoCard(
                    icon = if (isPositive) Icons.Rounded.ArrowOutward else Icons.Rounded.ArrowDownward,
                    title = stringResource(R.string.change_last_24h),
                    formattedText = "$ ${absoluteChangeFormatted.formatted}",
                    //contentColor = contentColor
                )

            }
        }

    }
}
