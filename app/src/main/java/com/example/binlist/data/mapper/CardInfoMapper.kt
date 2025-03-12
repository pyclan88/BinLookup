package com.example.binlist.data.mapper

import com.example.binlist.data.dto.CardInfoResponse
import com.example.binlist.domain.model.CardInfo

class CardInfoMapper {

    fun map(response: CardInfoResponse) = CardInfo(
        scheme = response.scheme,
        countryName = response.country?.name,
        latitude = response.country?.latitude,
        longitude = response.country?.longitude,
        url = response.bank?.url,
        phone = response.bank?.phone,
        city = response.bank?.city,
    )
}