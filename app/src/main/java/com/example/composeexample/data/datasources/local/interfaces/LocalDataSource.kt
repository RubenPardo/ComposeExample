package com.example.composeexample.data.datasources.local.interfaces

import com.example.composeexample.data.datasources.local.model.HeroLocal

interface LocalDataSource {
    suspend fun insertHeroList(heroList: List<HeroLocal>)
    suspend fun getHeroList() : List<HeroLocal>
    suspend fun getHeroById(id: String): HeroLocal
    suspend fun updateHero(hero: HeroLocal): Boolean

}