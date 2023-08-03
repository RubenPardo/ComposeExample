package com.example.composeexample.data.datasources.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.composeexample.data.datasources.remote.apis.BaseURL
import com.example.composeexample.data.datasources.remote.apis.DragonBallApi
import com.example.composeexample.data.datasources.remote.model.IdDto
import com.example.composeexample.data.datasources.remote.model.SearchDto
import com.example.composeexample.testUtil.DefaultDispatcherRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.hamcrest.CoreMatchers.*
import org.mockito.internal.matchers.GreaterThan
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class DragonBallApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()


    private lateinit var api: DragonBallApi

    @Before
    fun setup(){
        api = retrofit.create(DragonBallApi::class.java)
    }

    @Test
    fun `WHEN get heroes is called with a non existing hero name EXPECT empty list`() = runTest {
       val response =  api.getHeroes(SearchDto("dfdfndkjfndfdnfm"))
        assertThat(response.size, `is`(0))
    }

    @Test
    fun `WHEN get heroes is called with a existing hero name EXPECT one element`() = runTest {
        val response =  api.getHeroes(SearchDto("Goku"))
        assertThat(response.size, `is`(1))
    }

    @Test
    fun `WHEN get heroes is called with empty string EXPECT all elements`() = runTest {
        val response =  api.getHeroes(SearchDto(""))
        assertThat(response.size > 1, `is`(true))
    }


    @Test(expected = HttpException::class)
    fun `WHEN get heroes location called with random string EXPECT exception`() = runTest {
        api.getLocationsHero(
            IdDto("dsdsdsfdfd")
        )


        // no debe llegar aqui
        assert(false)
    }


    companion object {
        private  lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon(){
            retrofit = Retrofit.Builder()
                .baseUrl(BaseURL)
                .client(OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                            .apply {
                                level = HttpLoggingInterceptor.Level.NONE
                            }
                    ).build())
                .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()))
                .build()
        }
    }

}