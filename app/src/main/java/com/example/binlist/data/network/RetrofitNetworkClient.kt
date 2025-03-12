package com.example.binlist.data.network

import android.content.Context
import com.example.binlist.data.dto.CardInfoRequest
import com.example.binlist.data.dto.CardInfoResponse
import com.example.binlist.data.dto.Response
import com.example.binlist.util.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RetrofitNetworkClient(
    private val benListApi: BenListApi,
    private val context: Context,
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected(context)) {
            return Response().apply {
                resultCode = FAILED_INTERNET_CONNECTION_CODE
            }
        }

        return withContext(Dispatchers.IO) {
            try {
                if (dto is CardInfoRequest) {
                    getCardInfo(dto)
                } else {
                    Response().apply { resultCode = BAD_REQUEST_CODE }
                }
            } catch (e: HttpException) {
                val code = when (e.code()) {
                    TOO_MANY_REQUESTS_CODE, NOT_FOUND_CODE -> e.code()
                    else -> SERVER_ERROR_CODE
                }
                Response().apply { resultCode = code }
            }
        }
    }

    private suspend fun getCardInfo(
        input: CardInfoRequest
    ): CardInfoResponse {
        return benListApi.getCardInfo(
            bin = input.expression
        ).apply { resultCode = SUCCESS_CODE }
    }

    companion object {
        const val FAILED_INTERNET_CONNECTION_CODE = -1
        const val SUCCESS_CODE = 200
        const val BAD_REQUEST_CODE = 400
        const val NOT_FOUND_CODE = 404
        const val TOO_MANY_REQUESTS_CODE = 429
        const val SERVER_ERROR_CODE = 500
    }
}