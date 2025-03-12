package com.example.binlist.di

import com.example.binlist.ui.bincheck.BinCheckViewModel
import com.example.binlist.ui.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        BinCheckViewModel(
            getCardInfoUseCase = get(),
            addCardToHistoryUseCase = get(),
            mapNavigator = get(),
        )
    }

    viewModel { HistoryViewModel(getAllCardsFromHistoryUseCase = get()) }
}