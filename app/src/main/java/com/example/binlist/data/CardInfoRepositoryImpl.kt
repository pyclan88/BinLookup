package com.example.binlist.data

import com.example.binlist.data.dto.CardInfoRequest
import com.example.binlist.data.dto.CardInfoResponse
import com.example.binlist.data.mapper.CardInfoMapper
import com.example.binlist.data.network.NetworkClient
import com.example.binlist.data.network.RetrofitNetworkClient.Companion.SUCCESS_CODE
import com.example.binlist.domain.api.CardInfoRepository
import com.example.binlist.domain.model.CardInfo
import com.example.binlist.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardInfoRepositoryImpl(
    private val networkClient: NetworkClient,
    private val cardInfoMapper: CardInfoMapper,
) : CardInfoRepository {

    override suspend fun getCardInfo(expression: String): Resource<CardInfo> =
        withContext(Dispatchers.IO) {
            val response = networkClient.doRequest(CardInfoRequest(expression))
            when (response.resultCode) {
                SUCCESS_CODE -> {
                    val data = cardInfoMapper.map(response as CardInfoResponse)
                    Resource.Success(data)
                }

                else -> Resource.Error(response.resultCode.toString())
            }
        }
}