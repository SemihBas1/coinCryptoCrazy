package com.example.coincryptocrazy.repository

import com.example.coincryptocrazy.model.CryptoModel
import com.example.coincryptocrazy.util.Resource

interface CrpytoDownload {
    suspend fun downloadCryptos():Resource <List<CryptoModel>>

}