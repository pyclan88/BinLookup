package com.example.binlist.domain.api

import com.example.binlist.domain.model.CardInfo
import com.example.binlist.util.Resource

interface CardInfoRepository {
    suspend fun getCardInfo(expression: String): Resource<CardInfo>
}