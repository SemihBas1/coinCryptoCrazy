package com.example.coincryptocrazy.repository

import com.example.coincryptocrazy.model.CryptoModel
import com.example.coincryptocrazy.service.CryptoAPI
import com.example.coincryptocrazy.util.Resource

class CryptoModelImpl(private val api:CryptoAPI):CrpytoDownload {
    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {
        return try {
            val response=api.getData()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)

                }?:Resource.error("error",null)
            }else{
                Resource.error("Error",null)
            }

        }catch (e : java.lang.Exception){
            Resource.error("No data!",null)
        }

    }
}