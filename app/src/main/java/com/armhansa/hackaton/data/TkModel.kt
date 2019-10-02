package com.armhansa.hackaton.data

import com.example.myapplication.basic_api.data.StatusModel
import com.example.myapplication.basic_api.data.DataDetail
import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("status")
    val status: StatusModel?,

    @SerializedName("data")
    val data1: DataDetail?

)