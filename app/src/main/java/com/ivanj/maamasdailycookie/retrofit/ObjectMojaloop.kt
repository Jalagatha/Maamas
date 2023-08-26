package com.ivanj.maamasdailycookie.retrofit

import com.ivanj.maamasdailycookie.retrofit.RetrofitInstanceObject.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectMojaloop {


    val baseUrl_Initiate = "http://13.211.229.144:4040/thirdpartyTransaction/{ID}"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance_Approve(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}