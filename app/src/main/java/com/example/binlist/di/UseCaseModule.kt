package com.example.binlist.di

import com.example.binlist.domain.usecase.AddCardToHistoryUseCase
import com.example.binlist.domain.usecase.GetAllCardsFromHistoryUseCase
import com.example.binlist.domain.usecase.GetCardInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetCardInfoUseCase(repository = get()) }

    single { AddCardToHistoryUseCase(repository = get()) }

    single { GetAllCardsFromHistoryUseCase(repository = get()) }
}