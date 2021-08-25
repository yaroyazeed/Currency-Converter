package com.example.currencyconverter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.helper.Resource
import com.example.currencyconverter.helper.SingleLiveEvent
import com.example.currencyconverter.model.ApiResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.currencyconverter.model.Rates


class MainViewModel @ViewModelInject constructor(private val mainRepo: MainRepo) : ViewModel()  {


    //cached
    private val _data = SingleLiveEvent<Resource<ApiResponse>>()


    //public
    val data  =  _data
    val convertedRate = MutableLiveData<Double>()


    //Public function to get the result of conversion
    fun getConvertedData(access_key: String, from: String, to: String, amount: Double){
        viewModelScope.launch {
            mainRepo.getConvertedData(access_key, from, to, amount).collect {
                data.value = it
            }
        }

    }


}