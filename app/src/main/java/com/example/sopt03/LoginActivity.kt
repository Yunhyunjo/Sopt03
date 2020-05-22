package com.example.sopt03

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt03.data.RequestLogin
import com.example.sopt03.network.RequestToSever
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val requestToSever = RequestToSever //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_id.textChangedListener {
            if (it.isNullOrBlank()){
                showToast("아이디가 빈칸이네요")
            }
        }

        login.setOnClickListener {
            if(et_id.text.isNullOrBlank()||et_pwd.text.isNullOrBlank()){
                showToast("아이디와 비밀번호를 확인하세요")
            }else{
                //로그인 요청
                requestToSever.service.requestLogin(
                    RequestLogin(
                        id = et_id.text.toString(),
                        password = et_pwd.text.toString()
                    )//로그인 정보를 전달
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다.")},
                    onSuccess = {
                        if(it.success){
                            showToast("로그인 성공")
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            showToast("아이디/비밀번호를 확인하세요!")
                        }
                    }
                )
            }
        }
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent,0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                0 -> {
                    val id = data!!.getStringExtra("id")
                    et_id.setText(id)
                    val pwd = data!!.getStringExtra("pwd")
                    et_pwd.setText(pwd)
                }
            }
        }
    }
}
