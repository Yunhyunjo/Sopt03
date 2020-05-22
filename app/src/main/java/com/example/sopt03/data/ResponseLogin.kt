package com.example.sopt03.data

data class ResponseLogin (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Somedata?
)

data class Somedata(
    val jwt : String
)