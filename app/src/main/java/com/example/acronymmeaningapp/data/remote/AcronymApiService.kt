package com.example.acronymmeaningapp.data.remote

import com.example.acronymmeaningapp.domain.models.AcronymItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApiService {
    @GET(".")
    suspend fun getAcronymMeanings(@Query("sf") acronym: String): Response<List<AcronymItem>>
}