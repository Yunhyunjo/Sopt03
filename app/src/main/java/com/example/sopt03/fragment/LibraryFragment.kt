package com.example.sopt03.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt03.recycler.BookAdapter
import com.example.sopt03.R
import com.example.sopt03.customEnqueue
import com.example.sopt03.data.BookDatas
import com.example.sopt03.network.RequestToBook
import com.example.sopt03.showToast
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {

    lateinit var bookAdapter : BookAdapter
    val datas = mutableListOf<BookDatas>()
    val requestTobook = RequestToBook

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookAdapter = BookAdapter(view.context)
        book_list.adapter = bookAdapter

        search.setOnClickListener{
            if(et_bookTitle.text.isNullOrBlank()){
                context!!.showToast("제목을 입력하세요")
            }else{
                //로그인 요청
                requestTobook.service.requestSearchBook(
                        bookTitle = et_bookTitle.text.toString()
                ).customEnqueue(
                    onError = {context!!.showToast("올바르지 못한 요청입니다.")},
                    onSuccess = {
                        datas.clear()
                        val total : Int = it.documents.size
                        datas.addAll(it.documents)
                        context!!.showToast("검색 성공!")
                        bookAdapter.datas = datas
                        bookAdapter.notifyDataSetChanged()
                    }
                )
            }
        }

    }
}
