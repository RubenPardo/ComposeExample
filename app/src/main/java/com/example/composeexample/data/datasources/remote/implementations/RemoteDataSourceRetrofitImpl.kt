package com.example.composeexample.data.datasources.remote.implementations

import com.example.composeexample.data.datasources.remote.apis.DragonBallApi
import com.example.composeexample.data.datasources.remote.interfaces.RemoteDataSource
import com.example.composeexample.data.datasources.remote.model.HeroDTO
import com.example.composeexample.data.datasources.remote.model.IdDto
import com.example.composeexample.data.datasources.remote.model.LocationDto
import com.example.composeexample.data.datasources.remote.model.SearchDto
import com.example.composeexample.domain.model.Response

class RemoteDataSourceRetrofitImpl(
    private val dragonBallApi: DragonBallApi
) : RemoteDataSource {
    override suspend fun getHeroes(name: String): Response<List<HeroDTO>> = Response.Success(dragonBallApi.getHeroes(
        SearchDto(name)
    ))

    override suspend fun getLocationsHero(id:String): List<LocationDto> = dragonBallApi.getLocationsHero(
        IdDto(id)
    )
}