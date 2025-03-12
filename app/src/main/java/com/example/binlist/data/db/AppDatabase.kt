package com.example.binlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binlist.data.db.dao.HistoryCardDao
import com.example.binlist.data.db.entity.CardEntity

@Database(version = 1, entities = [CardEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun HistoryCardDao(): HistoryCardDao
}