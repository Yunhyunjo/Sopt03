# Sopt 3차 세미나 과제

✔Screenshot
---
<img src = "https://user-images.githubusercontent.com/37900920/82637582-0b523b80-9c40-11ea-8348-0d9ac41ff3df.gif">
---   
✔Learn
---

### 라이브러리

    //Retrofit 라이브러리 : https://github.com/square/retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    //Retrofit 라이브러리 응답으로 가짜 객체를 만들기 위해
    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    //객체 시리얼라이즈를 위한 Gson 라이브러리 : https://github.com/google/gson
    implementation 'com.google.code.gson:gson:2.8.6'
    //Retrofit에서 Gson을 사용하기 위한 라이브러리
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
 
### 1.Request 객체 설계

==> 서버에게 요청할 데이터 객체로 API문서를 보고 설계한다.

    data class RequestLogin (
        val id : String,
        val password : String
    )
  
### 2.Response 객체 설계
  
==> 서버에게 받을 데이터 객체로 마찬가지고 API문서를 보고 설계한다. 서버에서 정한 변수 말고직접 변수명을 정의하고   
싶다면 밑에 코드와 같이 __@SerializedName(" ")__ 이렇게 하면 된다.  
    
    data class ResponseLogin( 
      val status : Int, 
      val success : Boolean, 
      @SerializedName("data") 
      val responseData : SomeData?
    )

### 3. RtrofitInterface 설계

    interface RequestInteface {
        @POST("/user/signin")
        fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>
    }
    
### 4. Retrofit Interface 실제 구현체

    object RequestToSever {
        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.144.115:3333")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service: RequestInteface = retrofit.create(RequestInteface::class.java)
        var service2: RequestRegisterInteface = retrofit.create(RequestRegisterInteface::class.java)
    }
    
### 5. Callback 등록

==> 꼭 Retrofit의 Callback을 import해줘야 한다. 또 이 함수를 로그인 뿐이 아니라 다른 요청에서도 사용할 수 있게 확장함수로 만들어줬다. 요청에 따라 Reponse가 달라지므로 제네릭 타입으로 선언해줬다.

    fun <ResponseType> Call<ResponseType>.customEnqueue(
        onFail : () -> Unit = { Log.d("network","통신에 실패했습니다.")},
        onSuccess : (ResponseType) -> Unit,
        onError : () -> Unit
    ) {
        this.enqueue(object : Callback<ResponseType>{
            override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                onFail()
            }
            override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
                response.body()?.let { //body가 있다면 statusCode가 200-300사이입니다.
                    onSuccess(it) //통신
                } ?: onError() //rosponse.body()가 null값 -> statusCode가 200-300이 아닌 경우
            }
        })
    }

