package com.example.myapplication.basic_api.service
import com.example.myapplication.basic_api.data.ExampleData
import com.armhansa.hackaton.data.TokenModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SCBApiService {
   // @Headers("Content-Type: application/json","accept-language: EN","requestUId: 85230887-e643-4fa4-84b2-4e56709c4ac4","esourceOwnerId:l7f031d768df40465ba05ae327022a5220")
    @POST("v1/oauth/token")
    fun getToken(@Body exampleData: ExampleData): Call<TokenModel>
}