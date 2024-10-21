package com.giraffe.cryptotrackerapp.presentation.coin_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.cryptotrackerapp.R
import com.giraffe.cryptotrackerapp.core.toDisplayableNumber
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenState
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenVM
import com.giraffe.cryptotrackerapp.presentation.coins_list.previewState
import com.giraffe.cryptotrackerapp.presentation.components.InfoCard
import com.giraffe.cryptotrackerapp.presentation.components.chart.ChartStyle
import com.giraffe.cryptotrackerapp.presentation.components.chart.DataPoint
import com.giraffe.cryptotrackerapp.presentation.components.chart.LineChart
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinsListScreenVM = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    CoinDetailsContent(
        modifier = modifier,
        state = state
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsContent(
    modifier: Modifier = Modifier,
    state: CoinsListScreenState = previewState,
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
                InfoCard(
                    icon = if (isPositive) Icons.Rounded.ArrowOutward else Icons.Rounded.ArrowDownward,
                    title = stringResource(R.string.change_last_24h),
                    formattedText = "$ ${absoluteChangeFormatted.formatted}",
                    //contentColor = contentColor
                )

            }
            AnimatedVisibility(
                visible = state.selectedCoin.priceHistory.isNotEmpty()
            ) {
                var selectedDataPoint by remember {
                    mutableStateOf<DataPoint?>(null)
                }
                var labelWidth by remember {
                    mutableFloatStateOf(0f)
                }
                var totalChartWidth by remember {
                    mutableFloatStateOf(0f)
                }
                val amountOfVisibleDataPoints = if (labelWidth > 0) {
                    ((totalChartWidth - 2.5 * labelWidth) / labelWidth).toInt()
                } else {
                    0
                }
                val startIndex =
                    (state.selectedCoin.priceHistory.lastIndex - amountOfVisibleDataPoints)
                        .coerceAtLeast(0)
                LineChart(
                    dataPoints = state.selectedCoin.priceHistory,
                    style = ChartStyle(
                        chartLineColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary.copy(
                            alpha = 0.3f
                        ),
                        selectedColor = MaterialTheme.colorScheme.primary,
                        helperLinesThicknessPx = 5f,
                        axisLinesThicknessPx = 5f,
                        labelFontSize = 14.sp,
                        minYLabelSpacing = 25.dp,
                        verticalPadding = 8.dp,
                        horizontalPadding = 8.dp,
                        xAxisLabelSpacing = 8.dp
                    ),
                    visibleDataPointsIndices = startIndex..state.selectedCoin.priceHistory.lastIndex,
                    unit = "$",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .onSizeChanged { totalChartWidth = it.width.toFloat() },
                    selectedDataPoint = selectedDataPoint,
                    onSelectedDataPoint = {
                        selectedDataPoint = it
                    },
                    onXLabelWidthChange = { labelWidth = it }
                )
            }
            /*Box(modifier = Modifier.weight(1f)){
                LazyColumn {
                    items(state.priceHistory){
                        Text(it.priceUsd)
                    }
                }
            }*/

        }

    }
}
