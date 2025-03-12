package com.example.binlist.domain.usecase

import com.example.binlist.domain.api.CardInfoRepository
import com.example.binlist.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCardInfoUseCase(private val repository: CardInfoRepository) {

    suspend fun execute(expression: String) = withContext(Dispatchers.IO) {
        when (val result = repository.getCardInfo(expression)) {
            is Resource.Success -> Pair(result.data, null)
            is Resource.Error -> Pair(null, result.message)
        }
    }
}