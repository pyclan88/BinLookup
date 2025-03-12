package com.example.binlist.domain.usecase

import com.example.binlist.domain.api.HistoryRepository
import com.example.binlist.domain.model.CardInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCardToHistoryUseCase(
    private val repository: HistoryRepository
) {

    suspend fun execute(card: CardInfo) =
        withContext(Dispatchers.IO) { repository.add(card) }
}