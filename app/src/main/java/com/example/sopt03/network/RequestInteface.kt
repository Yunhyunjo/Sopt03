package com.example.sopt03.network

import com.example.sopt03.data.RequestLogin
import com.example.sopt03.data.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInteface {
    @POST("/user/signin")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>
}