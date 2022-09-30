package com.example.acronymmeaningapp.data.remote

import com.example.acronymmeaningapp.domain.models.AcronymItem
import com.example.acronymmeaningapp.domain.remote.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(
    private val acronymApiService: AcronymApiService
): RemoteDataSource {

    override suspend fun getAcronymMeanings(acronym: String): Response<List<AcronymItem>> {
        return acronymApiService.getAcronymMeanings(acronym)
    }
}