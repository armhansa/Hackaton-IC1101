package com.armhansa.hackaton.data

import com.google.gson.annotations.SerializedName

data class StatusModel(
    @SerializedName("code")
    val code: Number,
    @SerializedName("description")
    val description: String
)