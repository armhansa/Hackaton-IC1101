package com.armhansa.hackaton.data

import com.google.gson.annotations.SerializedName

data class TkModel(
    @SerializedName("status")
    val status: StatusModel?,
    @SerializedName("data")
    val data1: DataDetail?
)