package com.example.currencyconverter.viewmodel

import com.example.currencyconverter.helper.Resource
import com.example.currencyconverter.model.ApiResponse
import com.example.currencyconverter.network.ApiDataSource
import com.example.currencyconverter.network.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class MainRepo @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {

    //Using coroutines flow to get the response from
    suspend fun getConvertedData(access_key: String, from: String, to: String, amount: Double): Flow<Resource<ApiResponse>> {
        val convertedRate = apiDataSource.getConvertedRate(access_key, from, to, amount)
        return flow {
            emit(safeApiCall<FlowCollector<Resource<ApiResponse>>> { convertedRate })
        }.flowOn(Dispatchers.IO)
    }


}
