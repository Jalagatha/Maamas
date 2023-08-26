package com.ivanj.maamasdailycookie.retrofit

import com.ivanj.maamasdailycookie.adapters.CookieModelClass
import retrofit2.http.GET
import retrofit2.http.POST

interface MojaaloopApi {


    @POST("/initiate")
    suspend fun getAccountLookUp(): CookieModelClass

    @POST("/approve")
    suspend fun Approve(): CookieModelClass
}