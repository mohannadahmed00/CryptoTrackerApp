package com.giraffe.cryptotrackerapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.cryptotrackerapp.core.utils.domain_util.onError
import com.giraffe.cryptotrackerapp.core.utils.domain_util.onSuccess
import com.giraffe.cryptotrackerapp.domain.usecases.FetchCoinsUseCase
import com.giraffe.cryptotrackerapp.domain.usecases.GetCoinPriceHistoryUseCase
import com.giraffe.cryptotrackerapp.presentation.coins_list.CoinsListScreenActions
import com.giraffe.cryptotrackerapp.presentation.components.chart.DataPoint
import com.giraffe.cryptotrackerapp.presentation.ui_models.toCoinUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class CoinsSharedVM(
    private val fetchCoinsUseCase: FetchCoinsUseCase,
    private val getCoinPriceHistoryUseCase: GetCoinPriceHistoryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CoinsSharedState())
    val state = _state.onStart {
        fetchCoins()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), CoinsSharedState())
    private val _events = MutableSharedFlow<CoinsSharedEvents>()
    val events = _events.asSharedFlow()

    private fun fetchCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            fetchCoinsUseCase()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coinsList = coins.map { coin -> coin.toCoinUi() },
                            networkError = null
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, networkError = error) }
                    _events.emit(CoinsSharedEvents.Error(error))
                }
        }
    }

    @SuppressLint("NewApi")
    private fun getCoinPriceHistory(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCoinPriceHistoryUseCase(id)
                .onSuccess { priceHistory ->
                    val dataPoints = priceHistory
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = DateTimeFormatter
                                    .ofPattern("ha\nM/d")
                                    .format(it.dateTime)
                            )
                        }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            selectedCoin = it.selectedCoin?.copy(
                                priceHistory = dataPoints
                            )
                        )
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            networkError = error
                        )
                    }
                }
        }
    }

    fun onAction(action: CoinsListScreenActions) {
        when (action) {
            is CoinsListScreenActions.OnCoinClick -> {
                getCoinPriceHistory(action.coin.id)
                _state.update { it.copy(selectedCoin = action.coin) }
            }

            CoinsListScreenActions.OnRefresh -> fetchCoins()
        }
    }
}