package com.giraffe.cryptotrackerapp.crypto.presentation.coins_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoinsListVM : ViewModel() {
    private val _state = MutableStateFlow(CoinsListScreenState())
    val state = _state.asStateFlow()
}