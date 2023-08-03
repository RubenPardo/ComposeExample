package com.example.composeexample.data.repositories.interfaces

import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.LocationModel
import com.example.composeexample.domain.model.Response

interface DragonBallRepository {
    suspend fun getAllHeroes(name:String): Response<List<Hero>>
    suspend fun getHeroById(id: String): Hero
    suspend fun updateHero(hero:Hero): Boolean
    suspend fun getHeroLocations(id: String): List<LocationModel>
}