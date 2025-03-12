package com.example.binlist.ui.bincheck

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.data.network.RetrofitNetworkClient.Companion.FAILED_INTERNET_CONNECTION_CODE
import com.example.binlist.data.network.RetrofitNetworkClient.Companion.NOT_FOUND_CODE
import com.example.binlist.data.network.RetrofitNetworkClient.Companion.TOO_MANY_REQUESTS_CODE
import com.example.binlist.domain.model.CardInfo
import com.example.binlist.domain.state.CardInfoState
import com.example.binlist.domain.usecase.AddCardToHistoryUseCase
import com.example.binlist.domain.usecase.GetCardInfoUseCase
import kotlinx.coroutines.launch

class BinCheckViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val addCardToHistoryUseCase: AddCardToHistoryUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<CardInfoState>()
    val state: LiveData<CardInfoState>
        get() = _state

    fun requestToServer(expression: String) = viewModelScope.launch {
        _state.postValue(CardInfoState.Loading)
        val result = getCardInfoUseCase.execute(expression)
        val resultData = result.first
        val state: CardInfoState = when {

            resultData == null -> {
                when (result.second) {
                    FAILED_INTERNET_CONNECTION_CODE.toString() -> CardInfoState.NoInternet
                    NOT_FOUND_CODE.toString() -> CardInfoState.NoResult
                    TOO_MANY_REQUESTS_CODE.toString() -> CardInfoState.RateLimited
                    else -> CardInfoState.Error
                }
            }

            else -> CardInfoState.Content(resultData)
        }

        _state.postValue(state)
    }

    fun addCardToHistory(card: CardInfo) = viewModelScope.launch {
        addCardToHistoryUseCase.execute(card)
    }
}