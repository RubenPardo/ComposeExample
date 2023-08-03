package com.example.composeexample.data.datasources.remote.apis

import com.example.composeexample.data.datasources.remote.model.HeroDTO
import com.example.composeexample.data.datasources.remote.model.IdDto
import com.example.composeexample.data.datasources.remote.model.LocationDto
import com.example.composeexample.data.datasources.remote.model.SearchDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
const val BaseURL = "https://dragonball.keepcoding.education/api/"
const val TOKEN:String = "eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJleHBpcmF0aW9uIjo2NDA5MjIxMTIwMCwiZW1haWwiOiJiZWpsQGtlZXBjb2RpbmcuZXMiLCJpZGVudGlmeSI6IjdBQjhBQzRELUFEOEYtNEFDRS1BQTQ1LTIxRTg0QUU4QkJFNyJ9.lehepzhRj4gt7ThkRdsPI9aAEph8SgGLMZOjJ1534jI"
interface DragonBallApi{
    @POST("heros/all")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getHeroes(@Body name: SearchDto): List<HeroDTO>
    @POST("heros/locations")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getLocationsHero(@Body idDto: IdDto): List<LocationDto>
}