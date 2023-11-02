package com.loginapi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginInterface {
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("login")
    fun loginUser
        (@FieldMap params: MutableMap<String?, String?>?): Call<LoginResponse?>?
}