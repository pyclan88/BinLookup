package com.example.binlist.domain.state

import com.example.binlist.domain.model.CardInfo

sealed interface CardInfoState {
    data class Content(val cardInfo: CardInfo) : CardInfoState
    data object Loading : CardInfoState
    data object NoResult : CardInfoState
    data object Error : CardInfoState
    data object NoInternet : CardInfoState
    data object RateLimited : CardInfoState
}
