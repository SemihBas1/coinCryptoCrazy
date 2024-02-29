package com.example.coincryptocrazy.service

import com.example.coincryptocrazy.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData() : Response<List<CryptoModel>>
}