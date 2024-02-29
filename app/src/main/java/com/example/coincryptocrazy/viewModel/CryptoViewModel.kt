package com.example.coincryptocrazy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coincryptocrazy.model.CryptoModel
import com.example.coincryptocrazy.service.CryptoAPI
import com.example.coincryptocrazy.view.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel :ViewModel() {
    val cryptoList=MutableLiveData<List<CryptoModel>>()
    val cryptoError=MutableLiveData<Boolean>()
    val cryptoLoading=MutableLiveData<Boolean>()


    private var job: Job?=null
    val exceptionHandler= CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cryptoError.value=true

    }
    fun getDataFromApi(){
        cryptoLoading.value=true

        val BASE_URL="https://raw.githubusercontent.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)



        job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response= retrofit.getData()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    cryptoError.value=false
                    cryptoLoading.value=false
                    response.body()?.let {
                        cryptoList.value=it






                    }
                }
            }
        }

    }

}