package com.example.sopt03

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt03.data.BookDatas

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val book_cover = itemView.findViewById<ImageView>(R.id.book_cover)
    val book_title = itemView.findViewById<TextView>(R.id.book_title)
    val book_contents = itemView.findViewById<TextView>(R.id.book_contents)

    fun bind(bookData: BookDatas) {
        Glide.with(itemView).load(bookData.thumbnail).into(book_cover)
        book_title.text = bookData.title
        book_contents.text = bookData.contents
    }
}