package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.cryptotrackerapp.core.domain.util.onError
import com.giraffe.cryptotrackerapp.core.domain.util.onSuccess
import com.giraffe.cryptotrackerapp.crypto.domain.usecases.FetchCoinsUseCase
import com.giraffe.cryptotrackerapp.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsListScreenVM(
    private val fetchCoinsUseCase: FetchCoinsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CoinsListScreenState())
    val state = _state.onStart {
        fetchCoins()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), CoinsListScreenState())
    private val _events = Channel<CoinsListScreenEvents>()
    val events = _events.receiveAsFlow()

    private fun fetchCoins() {
        viewModelScope.launch {
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
                    _events.send(CoinsListScreenEvents.Error(error))
                }
        }
    }

    fun onAction(action: CoinsListScreenActions) {
        when (action) {
            is CoinsListScreenActions.OnCoinClick -> {}
            CoinsListScreenActions.OnRefresh -> fetchCoins()
        }
    }
}