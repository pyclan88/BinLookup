package com.example.binlist.data.dto

data class CardInfoResponse(
    val number: NumberResponse?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryResponse?,
    val bank: BankResponse?,
) : Response()

data class NumberResponse(
    val length: Int?,
    val luhn: Boolean?,
)

data class CountryResponse(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?,
)

data class BankResponse(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?,
)
