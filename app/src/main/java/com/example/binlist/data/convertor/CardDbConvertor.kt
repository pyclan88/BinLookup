package com.example.binlist.data.convertor

import com.example.binlist.data.db.entity.CardEntity
import com.example.binlist.domain.model.CardInfo

class CardDbConvertor {

    fun map(cardInfo: CardInfo): CardEntity {
        return CardEntity(
            cardInfo.id,
            cardInfo.bin,
            cardInfo.scheme,
            cardInfo.countryName,
            cardInfo.latitude,
            cardInfo.longitude,
            cardInfo.url,
            cardInfo.phone,
            cardInfo.city,
            cardInfo.addedAt,
        )
    }

    fun map(cardEntity: CardEntity): CardInfo {
        return CardInfo(
            cardEntity.id,
            cardEntity.bin,
            cardEntity.scheme,
            cardEntity.countryName,
            cardEntity.latitude,
            cardEntity.longitude,
            cardEntity.url,
            cardEntity.phone,
            cardEntity.city,
            cardEntity.addedAt
        )
    }
}