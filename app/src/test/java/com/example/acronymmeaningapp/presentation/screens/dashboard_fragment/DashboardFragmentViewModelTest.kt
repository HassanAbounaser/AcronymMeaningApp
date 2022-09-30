package com.example.acronymmeaningapp.presentation.screens.dashboard_fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.acronymmeaningapp.domain.models.LongForm
import com.example.acronymmeaningapp.domain.use_case.GetAcronymMeaningsUseCase
import com.example.acronymmeaningapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DashboardFragmentViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var dashboardFragmentViewModel: DashboardFragmentViewModel
    private lateinit var getAcronymMeaningsUseCase: GetAcronymMeaningsUseCase

    @Before
    fun setup() {
        getAcronymMeaningsUseCase = mockk()
        dashboardFragmentViewModel = DashboardFragmentViewModel(getAcronymMeaningsUseCase)
    }

    @Test
    fun testingThatLoadingIsTriggeredWhenGetAcronymMeaningsIsCalled() = runTest {
        val acronym = "aaa"
        val loadingObserver = mockk<Observer<Boolean>>()
        val loadingSlot = slot<Boolean>()
        val loadingUpdateList = mutableListOf<Boolean>()
        val response = listOf(LongForm(lf = "aye aye aye", freq = 1, since = 1, vars = listOf()))

        coEvery { getAcronymMeaningsUseCase(acronym) } coAnswers { listOf() }
        coEvery { loadingObserver.onChanged(capture(loadingSlot)) } coAnswers {
            loadingUpdateList.add(loadingSlot.captured)
        }

        dashboardFragmentViewModel.loading.observeForever(loadingObserver)

        dashboardFragmentViewModel.getAcronymMeanings()

        val (startsOutFalse, thenShouldBeTrue, finallyBackToFalse) = loadingUpdateList

        assertFalse(startsOutFalse)
        assertTrue(thenShouldBeTrue)
        assertFalse(finallyBackToFalse)

        assertEquals(3, loadingUpdateList.size)
    }

    @Test
    fun testingThatErrorIsTriggeredWhenGetAcronymMeaningsIsCalledAndExceptionThrown() =
        runTest {
            val acronym = "aaa"
            val exceptionObserver = mockk<Observer<String>>()
            val exceptionSlot = slot<String>()
            val exceptionMessageUpdateList = mutableListOf<String>()

            coEvery { getAcronymMeaningsUseCase(acronym) } throws Exception("")
            coEvery { exceptionObserver.onChanged(capture(exceptionSlot)) } coAnswers {
                exceptionMessageUpdateList.add(exceptionSlot.captured)
            }

            dashboardFragmentViewModel.exceptionMessage.observeForever(exceptionObserver)

            dashboardFragmentViewModel.getAcronymMeanings()

            println(exceptionMessageUpdateList)

            assertEquals(3, exceptionMessageUpdateList.size)
        }
}
