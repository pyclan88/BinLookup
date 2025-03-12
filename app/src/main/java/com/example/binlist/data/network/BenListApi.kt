package com.example.binlist.data.network

import com.example.binlist.data.dto.CardInfoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BenListApi {
    @GET("/{bin}")
    suspend fun getCardInfo(
        @Path("bin") bin: String,
        @Header("Accept-Version") version: String = "3"
    ): CardInfoResponse
}