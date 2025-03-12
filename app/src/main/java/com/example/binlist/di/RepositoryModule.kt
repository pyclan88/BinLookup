package com.example.binlist.di

import com.example.binlist.data.CardInfoRepositoryImpl
import com.example.binlist.data.HistoryRepositoryImpl
import com.example.binlist.domain.api.CardInfoRepository
import com.example.binlist.domain.api.HistoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CardInfoRepository> {
        CardInfoRepositoryImpl(
            networkClient = get(),
            cardInfoMapper = get(),
        )
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(
            historyCardDao = get(),
            convertor = get(),
        )
    }
}