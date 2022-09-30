package com.example.acronymmeaningapp.domain.remote

import com.example.acronymmeaningapp.domain.models.AcronymItem
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getAcronymMeanings(acronym: String): Response<List<AcronymItem>>
}