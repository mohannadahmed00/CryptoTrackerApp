package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giraffe.cryptotrackerapp.R
import com.giraffe.cryptotrackerapp.core.toDisplayableNumber
import com.giraffe.cryptotrackerapp.crypto.presentation.models.CoinUi

@Composable
fun CoinItem(
    coinUi: CoinUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()

            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            modifier = Modifier.size(85.dp),
            imageVector = ImageVector.vectorResource(coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coinUi.symbol,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = coinUi.name,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${coinUi.priceUsd.formatted}$",
                fontSize = 18.sp
            )
            PriceChange(price = coinUi.priceUsd)
        }
    }
}

@Preview
@Composable
fun CoinItemPreview() {
    CoinItem(previewCoinUi)
}

internal val previewCoinUi = CoinUi(
    id = "0",
    name = "Bit Coin",
    symbol = "BTC",
    rank = 150,
    priceUsd = (-14.2445).toDisplayableNumber(),
    marketCapUsd = 155.51565.toDisplayableNumber(),
    changePercent24Hr = 1656.315.toDisplayableNumber(),
    iconRes = R.drawable.btc,
)
