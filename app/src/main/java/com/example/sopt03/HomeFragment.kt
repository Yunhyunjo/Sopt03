package com.example.sopt03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt03.data.InstaData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var  instaAdapter: InstaAdapter
    val datas = mutableListOf<InstaData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter = InstaAdapter(view.context)
        rv_home.adapter = instaAdapter
        loadDatas()
    }

    private fun loadDatas() {
        datas.apply{
            add(
                InstaData(
                    username = "고양이",
                    img_profile = "https://img.etoday.co.kr/pto_db/2018/01/20180118101220_1176870_710_340.jpg",
                    img_contents = "https://www.dailysecu.com/news/photo/201808/37483_30044_5401.jpg"
                ))
            add(
                InstaData(
                    username = "너무",
                    img_profile = "https://img.etoday.co.kr/pto_db/2018/01/20180118101220_1176870_710_340.jpg",
                    img_contents = "https://i.pinimg.com/originals/60/6b/3d/606b3d53635879b3ce083c5b2ca1080b.jpg"
                ))
            add(
                InstaData(
                    username = "귀엽다",
                    img_profile = "https://img.etoday.co.kr/pto_db/2018/01/20180118101220_1176870_710_340.jpg",
                    img_contents = "https://m.the-pet.co.kr/web/product/medium/20191224/6f5ba5295e975bbbc6bdc05b4bd481cf.jpg"
                ))
        }
        instaAdapter.datas = datas
        instaAdapter.notifyDataSetChanged()
    }
}
