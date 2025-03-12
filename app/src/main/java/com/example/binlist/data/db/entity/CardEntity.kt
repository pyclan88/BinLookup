package com.example.binlist.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bin: String,
    val scheme: String?,
    val countryName: String?,
    val latitude: Double?,
    val longitude: Double?,
    val url: String?,
    val phone: String?,
    val city: String?,
    val addedAt: String,
)
