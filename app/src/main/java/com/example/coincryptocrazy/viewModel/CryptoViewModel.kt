package com.example.coincryptocrazy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coincryptocrazy.model.CryptoModel
import com.example.coincryptocrazy.repository.CrpytoDownload
import com.example.coincryptocrazy.service.CryptoAPI
import com.example.coincryptocrazy.util.Resource
import com.example.coincryptocrazy.view.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel(private val cryptoDownloadRepository:CrpytoDownload) :ViewModel() {
    val cryptoList=MutableLiveData<Resource<List<CryptoModel>>>()
    val cryptoError=MutableLiveData<Resource<Boolean>>()
    val cryptoLoading=MutableLiveData<Resource<Boolean>>()


    private var job: Job?=null
    val exceptionHandler= CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
        cryptoError.value=Resource.error(throwable.localizedMessage?:"error",data=true)

    }
    fun getDataFromApi(){
        cryptoLoading.value=Resource.loading(data=true)

        /*
        */



        job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val resource= cryptoDownloadRepository.downloadCryptos()
            withContext(Dispatchers.Main){
                resource.data?.let {
                    cryptoList.value=resource
                    cryptoLoading.value=Resource.loading(data=false)
                    cryptoError.value=Resource.error("",false)
                }

            }
        }

    }

}