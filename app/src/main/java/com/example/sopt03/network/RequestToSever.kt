package com.example.sopt03.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RequestToSever {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.209.144.115:3333")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInteface = retrofit.create(RequestInteface::class.java)
    var service2: RequestRegisterInteface = retrofit.create(RequestRegisterInteface::class.java)
}