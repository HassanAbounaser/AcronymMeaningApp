package com.example.acronymmeaningapp.domain.models


import com.google.gson.annotations.SerializedName

data class AcronymItem(
    @SerializedName("sf")
    val sf: String,
    @SerializedName("lfs")
    val lfs: List<LongForm>
)