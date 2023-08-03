package com.example.composeexample.presentation.listHeroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import com.example.composeexample.domain.model.HeroTestDataBuilder
import com.example.composeexample.domain.model.Response
import com.example.composeexample.domain.usecases.GetAllHeroesUseCase
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.example.composeexample.testUtil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeroListViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getHeroListUseCase: GetAllHeroesUseCase

    @MockK
    lateinit var heroListViewModel: HeroListViewModel


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        heroListViewModel = HeroListViewModel(getHeroListUseCase)

    }

    @Test
    fun `WHEN Get Hero List Use Case return a Hero List EXPECT Init, Loading, Loaded States`() = runTest {
        val expectedHeroes = HeroTestDataBuilder()
            .withNumElements(15)
            .buildList()

        coEvery { getHeroListUseCase.invoke() } returns Response.Success(expectedHeroes)


        heroListViewModel.uiState.test {

            // action
            heroListViewModel.initialize()

            assertEquals(HeroListUiState.InitState, awaitItem())
            assertEquals(HeroListUiState.Loading, awaitItem())
            assertEquals(HeroListUiState.Loaded(expectedHeroes), awaitItem())
        }


    }

    @Test
    fun `WHEN Get Hero List Use Case return an Error EXPECT Init, Loading, Error States`() = runTest {
        val expectedError = "Unable to get Heroes"

        coEvery { getHeroListUseCase.invoke() } returns Response.Error(expectedError)


        heroListViewModel.uiState.test {

            // action
            heroListViewModel.initialize()

            assertEquals(HeroListUiState.InitState, awaitItem())
            assertEquals(HeroListUiState.Loading, awaitItem())
            assertEquals(HeroListUiState.Error(expectedError), awaitItem())
        }


    }


}