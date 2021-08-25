package com.example.currencyconverter.network

import android.util.Log
import com.example.currencyconverter.helper.Resource
import com.example.currencyconverter.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.lang.Exception

/**
 * This helps to properly handle the response gotten from the API - Be it error, success etc
 */

abstract class BaseDataSource {

      fun <T> safeApiCall(apiCall: () -> Response<ApiResponse>): Resource<ApiResponse> {

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}