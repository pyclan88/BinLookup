package com.example.binlist.data.network

import com.example.binlist.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}