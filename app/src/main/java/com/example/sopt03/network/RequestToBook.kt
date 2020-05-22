package com.example.sopt03.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RequestToBook {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: RequestBookInteface = retrofit.create(RequestBookInteface::class.java)
}