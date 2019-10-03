package com.example.myapplication.basic_api.data

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("data")
    val data: dataDetail?

)