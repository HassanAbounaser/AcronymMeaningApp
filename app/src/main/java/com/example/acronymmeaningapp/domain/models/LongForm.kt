package com.example.acronymmeaningapp.domain.models


import com.google.gson.annotations.SerializedName

data class LongForm(
    @SerializedName("lf")
    val lf: String,
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("since")
    val since: Int,
    @SerializedName("vars")
    val vars: List<Var>
)