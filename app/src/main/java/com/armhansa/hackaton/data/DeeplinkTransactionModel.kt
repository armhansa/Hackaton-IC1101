package com.armhansa.hackaton.data

import com.google.gson.annotations.SerializedName

data class DeeplinkTransactionModel(
    @SerializedName("status")
    val status: StatusModel?,
    @SerializedName("data")
    val data: DeeplinkTransactionData?
)