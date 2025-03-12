package com.example.binlist.domain.usecase

import com.example.binlist.domain.api.HistoryRepository
import com.example.binlist.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

class GetAllCardsFromHistoryUseCase(private val repository: HistoryRepository) {

    fun execute(): Flow<List<CardInfo>> {
        return repository.getAll()
    }
}