package com.example.sopt03.data

data class BookData (
    val documents : List<BookDatas>
)

data class BookDatas(
    val title : String,
    val contents : String,
    val thumbnail : String
)