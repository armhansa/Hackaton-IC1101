package com.armhansa.hackaton.data

import com.google.gson.annotations.SerializedName

data class DeeplinkTransactionData (
    @SerializedName("transactionId")
    val transactionId : String?,

    @SerializedName("deeplinkUrl")
    val deeplinkUrl : String?,

    @SerializedName("userRefId")
    val userRefId : String?
)