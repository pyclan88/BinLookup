package com.example.binlist.domain.state

import com.example.binlist.domain.model.CardInfo

interface HistoryCardState {
    data object Empty : HistoryCardState
    data class Data(val cards: List<CardInfo>) : HistoryCardState
}