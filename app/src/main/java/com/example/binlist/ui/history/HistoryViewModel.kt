package com.example.binlist.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.model.CardInfo
import com.example.binlist.domain.state.HistoryCardState
import com.example.binlist.domain.usecase.GetAllCardsFromHistoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryViewModel(private val getAllCardsFromHistoryUseCase: GetAllCardsFromHistoryUseCase) :
    ViewModel() {

    private val _state = MutableLiveData<HistoryCardState>()
    val state: LiveData<HistoryCardState>
        get() = _state

    fun getHistoryCards() = viewModelScope.launch {
        val cardsFlow: Flow<List<CardInfo>> = getAllCardsFromHistoryUseCase.execute()
        cardsFlow.collect { cards ->
            val state = if (cards.isEmpty()) {
                HistoryCardState.Empty
            } else {
                HistoryCardState.Data(cards)
            }

            _state.value = state
        }
    }
}