package com.example.acronymmeaningapp.domain.use_case

import com.example.acronymmeaningapp.domain.models.LongForm
import com.example.acronymmeaningapp.domain.repository.Repository
import javax.inject.Inject

class GetAcronymMeaningsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(acronym: String): List<LongForm> {
        return repository.getAcronymMeanings(acronym)
    }
}
