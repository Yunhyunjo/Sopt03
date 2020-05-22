package com.example.sopt03.network

import com.example.sopt03.data.BookData
import com.example.sopt03.data.RequestRegister
import com.example.sopt03.data.ResponseRegister
import retrofit2.Call
import retrofit2.http.*

interface RequestBookInteface {
    @Headers("Authorization: KakaoAK 70636563ddb4bc9194e7f58749c3f93a")
    @GET("/v3/search/book?target=bookTitle")
    fun requestSearchBook( @Query("query")bookTitle : String ) : Call<BookData>
}