package com.example.binlist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.binlist.data.db.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryCardDao {

    @Insert
    suspend fun insert(card: CardEntity)

    @Query("SELECT * FROM card_table ORDER BY addedAt DESC")
    fun getAll(): Flow<List<CardEntity>>
}