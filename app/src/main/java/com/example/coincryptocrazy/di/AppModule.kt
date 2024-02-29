package com.example.coincryptocrazy.di

import com.example.coincryptocrazy.repository.CrpytoDownload
import com.example.coincryptocrazy.repository.CryptoModelImpl
import com.example.coincryptocrazy.service.CryptoAPI
import com.example.coincryptocrazy.viewModel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule= module { single {
    val BASE_URL="https://raw.githubusercontent.com/"
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoAPI::class.java)
}
    single<CrpytoDownload> {
        CryptoModelImpl(get())
    }
    viewModel {CryptoViewModel(get())


    }
}