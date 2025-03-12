package com.example.binlist.domain.api

import com.example.binlist.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun add(card: CardInfo)
    fun getAll(): Flow<List<CardInfo>>
}