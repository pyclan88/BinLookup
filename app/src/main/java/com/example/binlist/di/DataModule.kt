package com.example.binlist.di

import androidx.room.Room
import com.example.binlist.data.convertor.CardDbConvertor
import com.example.binlist.data.db.AppDatabase
import com.example.binlist.data.mapper.CardInfoMapper
import com.example.binlist.data.network.BenListApi
import com.example.binlist.data.network.NetworkClient
import com.example.binlist.data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BIN_LIST_BASE_URL = "https://lookup.binlist.net"

val dataModule = module {

    single<BenListApi> {
        Retrofit.Builder()
            .baseUrl(BIN_LIST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BenListApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(
            benListApi = get(),
            context = androidContext(),
        )
    }

    factory { CardInfoMapper() }

    factory { CardDbConvertor() }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().HistoryCardDao() }
}