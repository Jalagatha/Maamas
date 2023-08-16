package com.ivanj.maamasdailycookie.retrofit

import com.ivanj.maamasdailycookie.adapters.CookieModelClass
import retrofit2.http.GET

interface Api {


    @GET("/getcookies.php")
    suspend fun getCookies(): CookieModelClass
}