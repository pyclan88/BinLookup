package com.example.binlist.app

import android.app.Application
import com.example.binlist.di.dataModule
import com.example.binlist.di.presentationModule
import com.example.binlist.di.repositoryModule
import com.example.binlist.di.useCaseModule
import com.example.binlist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BinListApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BinListApp)
            modules(
                viewModelModule,
                dataModule,
                repositoryModule,
                useCaseModule,
                presentationModule
            )
        }
    }
}