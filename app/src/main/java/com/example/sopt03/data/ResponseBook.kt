package com.example.sopt03.data

data class ResponseBook (
    val title : String,
    val contents : String,
    val thumbnail : String,
    val total_count : Int,
    val success : Int
)