package com.example.binlist.data

import com.example.binlist.data.convertor.CardDbConvertor
import com.example.binlist.data.db.dao.HistoryCardDao
import com.example.binlist.domain.api.HistoryRepository
import com.example.binlist.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HistoryRepositoryImpl(
    private val historyCardDao: HistoryCardDao,
    private val convertor: CardDbConvertor
) : HistoryRepository {
    override suspend fun add(card: CardInfo) {
        val formattedDate = LocalDateTime.now().format(DATE_TIME_FORMATTER)
        val datedCard = card.copy(addedAt = formattedDate)
        historyCardDao.insert(convertor.map(datedCard))
    }

    override fun getAll(): Flow<List<CardInfo>> {
        val cardsEntitiesFlow = historyCardDao.getAll()
        val cardsFlow = cardsEntitiesFlow.map { cardsEntities ->
            cardsEntities.map { convertor.map(it) }
        }
        return cardsFlow
    }

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    }
}