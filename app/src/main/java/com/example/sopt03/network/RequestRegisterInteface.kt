package com.example.sopt03.network

import com.example.sopt03.data.RequestRegister
import com.example.sopt03.data.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestRegisterInteface {
    @POST("/user/signup")
    fun requestRegister(@Body body : RequestRegister) : Call<ResponseRegister>
}