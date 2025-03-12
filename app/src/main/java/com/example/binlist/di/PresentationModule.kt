package com.example.binlist.di

import com.example.binlist.ui.navigation.MapNavigator
import org.koin.dsl.module

val presentationModule = module {
    single { MapNavigator(get()) }
}