package com.example.acronymmeaningapp.domain.repository

import com.example.acronymmeaningapp.domain.models.LongForm

interface Repository {

    suspend fun getAcronymMeanings(acronym: String): List<LongForm>
}