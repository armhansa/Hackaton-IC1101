package com.armhansa.hackaton.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataDetail(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("tokenType")
    val tokenType: String,
    @SerializedName("expiresIn")
    val expiresIn: Number,
    @SerializedName("expiresAt")
    val expiresAt: Number
) : Parcelable