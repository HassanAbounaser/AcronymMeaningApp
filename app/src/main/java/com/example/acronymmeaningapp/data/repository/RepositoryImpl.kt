package com.example.acronymmeaningapp.data.repository

import com.example.acronymmeaningapp.domain.models.AcronymItem
import com.example.acronymmeaningapp.domain.models.LongForm
import com.example.acronymmeaningapp.domain.remote.RemoteDataSource
import com.example.acronymmeaningapp.domain.repository.Repository
import com.example.acronymmeaningapp.utils.NullOrEmptyResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getAcronymMeanings(acronym: String): List<LongForm> =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.getAcronymMeanings(acronym)
            if (!response.isSuccessful) {
                throw HttpException(response)
            } else {
                val arrayListOfAcronymItems: List<AcronymItem>? = response.body()
                if (arrayListOfAcronymItems.isNullOrEmpty()) {
                    throw NullOrEmptyResponseException(response)
                } else {
                    arrayListOfAcronymItems[0].lfs
                }
            }
        }
}
