package com.example.acronymmeaningapp.utils

import com.example.acronymmeaningapp.domain.models.AcronymItem
import retrofit2.Response

class NullOrEmptyResponseException(response: Response<List<AcronymItem>>) : Exception()
