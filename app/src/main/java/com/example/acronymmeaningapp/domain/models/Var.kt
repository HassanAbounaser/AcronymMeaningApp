package com.example.acronymmeaningapp.domain.models


import com.google.gson.annotations.SerializedName

data class Var(
    @SerializedName("lf")
    val lf: String,
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("since")
    val since: Int
)