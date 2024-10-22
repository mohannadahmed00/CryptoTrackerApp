package com.giraffe.cryptotrackerapp.core.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.ObserveAsEvents
import com.giraffe.cryptotrackerapp.core.utils.presentation_util.showToast
import com.giraffe.cryptotrackerapp.presentation.coin_details.CoinDetailsScreen
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreen
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenActions
import com.giraffe.cryptotrackerapp.presentation.viewmodel.CoinsSharedEvents
import com.giraffe.cryptotrackerapp.presentation.viewmodel.CoinsSharedVM
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveCoinListDetailPane(
    viewModel: CoinsSharedVM = koinViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is CoinsSharedEvents.Error -> event.error.showToast(context)
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinsListScreen { action ->
                    when (action) {
                        is CoinsListScreenActions.OnCoinClick -> {
                            viewModel.onAction(action)
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail
                            )
                        }

                        CoinsListScreenActions.OnRefresh -> viewModel.onAction(action)
                    }
                }

            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailsScreen()
            }
        },
    )
}