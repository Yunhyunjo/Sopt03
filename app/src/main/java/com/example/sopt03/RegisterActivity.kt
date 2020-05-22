package com.example.sopt03

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt03.data.RequestRegister
import com.example.sopt03.network.RequestToSever
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val requestToSever = RequestToSever
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register.setOnClickListener {
            if(et_id.text.isNullOrBlank()||et_pwd.text.isNullOrBlank()){
                showToast("아이디와 비밀번호를 확인하세요.")
            }else{
                requestToSever.service2.requestRegister(
                    RequestRegister(
                        id = et_id.text.toString(),
                        password = et_pwd.text.toString(),
                        name = name.text.toString(),
                        email = email.text.toString(),
                        phone = phone.text.toString()
                    )
                ).customEnqueue(
                    onError = {showToast("올바르지 못한 요청입니다.")},
                    onSuccess = {
                        if(it.success)
                        {
                            showToast("회원가입 성공")
                            intent.putExtra("id", et_id.text.toString())
                            intent.putExtra("pwd", et_pwd.text.toString())
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        }else{
                            showToast("아이디/비밀번호를 확인하세요")
                        }
                })
            }
        }
    }
}
