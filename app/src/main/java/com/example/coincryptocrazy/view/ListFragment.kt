package com.example.coincryptocrazy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coincryptocrazy.databinding.FragmentListBinding
import com.example.coincryptocrazy.model.CryptoModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job


class ListFragment : Fragment() {
    private var _binding:FragmentListBinding?=null
    private val binding get()=_binding
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>?=null
    private var job: Job?=null

    val exceptionHandler= CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }


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

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}