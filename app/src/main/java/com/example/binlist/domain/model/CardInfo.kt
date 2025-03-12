package com.example.binlist.domain.model

import com.example.binlist.common.AppConstants.EMPTY_INT_PARAM_VALUE
import com.example.binlist.common.AppConstants.EMPTY_STRING_PARAM_VALUE

data class CardInfo(
    val id: Int = EMPTY_INT_PARAM_VALUE,
    val bin: String = EMPTY_STRING_PARAM_VALUE,
    val scheme: String?,
    val countryName: String?,
    val latitude: Double?,
    val longitude: Double?,
    val url: String?,
    val phone: String?,
    val city: String?,
    val addedAt: String = EMPTY_STRING_PARAM_VALUE
)
