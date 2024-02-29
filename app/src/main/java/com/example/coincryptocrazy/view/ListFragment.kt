package com.example.coincryptocrazy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coincryptocrazy.databinding.FragmentListBinding
import com.example.coincryptocrazy.model.CryptoModel
import com.example.coincryptocrazy.service.CryptoAPI
import com.example.coincryptocrazy.viewModel.CryptoViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(),RecyclerViewAdapter.Listener {
    private var _binding:FragmentListBinding?=null
    private val binding get()=_binding
    private var cryptoAdapter=RecyclerViewAdapter(arrayListOf(),this)
    private lateinit var viewModel: CryptoViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.layoutManager=layoutManager
        viewModel=ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.getDataFromApi()
        observeLiveData()
    }
  private fun observeLiveData(){
      viewModel.cryptoList.observe(viewLifecycleOwner, Observer { cryptos->
          cryptos?.let {
              binding?.recyclerView?.visibility=View.VISIBLE
              cryptoAdapter= RecyclerViewAdapter(ArrayList(cryptos),this@ListFragment)
              binding?.recyclerView?.adapter=cryptoAdapter

          }
      })
      viewModel.cryptoError.observe(viewLifecycleOwner, Observer {error->
          error?.let{
              if(it){
                  binding?.cryptoErrorText?.visibility=View.VISIBLE
                  binding?.recyclerView?.visibility=View.GONE
              }else{
                  binding?.cryptoErrorText?.visibility=View.GONE

              }


          }
      })
      viewModel.cryptoLoading.observe(viewLifecycleOwner, Observer { loading->
          loading?.let {
              if(it){
                  binding?.cryptoProgressbar?.visibility=View.VISIBLE
                  binding?.recyclerView?.visibility=View.GONE
                  binding?.cryptoErrorText?.visibility=View.GONE

              }else{
                  binding?.cryptoProgressbar?.visibility=View.GONE

              }


          }
      })

  }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(),"Clicked on :${cryptoModel.currency}",Toast.LENGTH_SHORT).show()
    }

}