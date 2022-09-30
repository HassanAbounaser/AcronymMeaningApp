package com.example.acronymmeaningapp.presentation.screens.dashboard_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymmeaningapp.domain.models.LongForm
import com.example.acronymmeaningapp.domain.use_case.GetAcronymMeaningsUseCase
import com.example.acronymmeaningapp.utils.NullOrEmptyResponseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DashboardFragmentViewModel @Inject constructor(
    private val getAcronymMeaningsUseCase: GetAcronymMeaningsUseCase
) : ViewModel() {
    val acronymMeaningsViewState = MutableLiveData<List<LongForm>>()
    var userAcronymInput: String = ""
    val exceptionMessage = MutableLiveData("")
    val loading = MutableLiveData(false)
    private val genericExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val thrownMessage = when (throwable) {
            is CancellationException -> "Operation is Canceled!"
            is NullOrEmptyResponseException -> "No meanings found!"
            is IOException -> "Check your network connection!"
            is HttpException -> throwable.message()
            else -> "Unknown Error!"
        }
        exceptionMessage.value = thrownMessage
        loading.value = false
    }

    fun getAcronymMeanings() {
        acronymMeaningsViewState.value = emptyList()
        exceptionMessage.value = ""
        loading.value = true
        viewModelScope.launch(genericExceptionHandler) {
            val acronymMeanings = getAcronymMeaningsUseCase(userAcronymInput)
            acronymMeaningsViewState.value = acronymMeanings
            loading.value = false
        }
    }
}
