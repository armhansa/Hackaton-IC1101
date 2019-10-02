package com.example.myapplication.basic_api.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TokenModel (
        @SerializedName("data") val data1 : List<DataDetail>

) : Parcelable