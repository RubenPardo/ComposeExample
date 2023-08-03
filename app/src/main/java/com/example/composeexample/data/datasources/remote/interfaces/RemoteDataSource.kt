package com.example.composeexample.data.datasources.remote.interfaces

import com.example.composeexample.data.datasources.remote.model.HeroDTO
import com.example.composeexample.data.datasources.remote.model.LocationDto
import com.example.composeexample.domain.model.Response

interface RemoteDataSource {
    suspend fun getHeroes(name:String): Response<List<HeroDTO>>
    suspend fun getLocationsHero(id:String): List<LocationDto>
}