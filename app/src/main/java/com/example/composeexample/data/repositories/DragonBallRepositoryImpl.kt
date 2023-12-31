package com.example.composeexample.data.repositories

import android.util.Log
import com.example.composeexample.data.datasources.local.interfaces.LocalDataSource
import com.example.composeexample.data.datasources.remote.interfaces.RemoteDataSource
import com.example.composeexample.data.mappers.toDomain
import com.example.composeexample.data.mappers.toLocal
import com.example.composeexample.data.repositories.interfaces.DragonBallRepository
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.LocationModel
import com.example.composeexample.domain.model.Response

class DragonBallRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): DragonBallRepository {


    override suspend fun getAllHeroes(name:String): Response<List<Hero>> {
        return try {
            val localData = localDataSource.getHeroList()

            if(localData.isNotEmpty()){
                Log.d("GET --- ", "getAllHeroes: DESDE LOCAL")
                return Response.Success(localData.map { it.toDomain() })
            }else{
                Log.d("GET --- ", "getAllHeroes: DESDE REMOTE")
                when(val heroesDTO = remoteDataSource.getHeroes(name)){
                    is Response.Error -> Response.Error("Unable to get heroes")
                    is Response.Success -> {
                        // guardar localmente

                        val heroesDO = heroesDTO.data?.let {
                            localDataSource.insertHeroList(it.map { heroDTO -> heroDTO.toLocal() })
                            it.map { hero-> hero.toDomain() }
                        }
                        return heroesDO?.let {
                            Response.Success(it)
                        } ?: Response.Error("")

                    }
                }
            }

        }catch (e:Exception){
            Response.Error(e.message)
        }

    }

    override suspend fun getHeroById(id: String): Hero {
        return localDataSource.getHeroById(id).toDomain()
    }

    override suspend fun updateHero(hero:Hero): Boolean = localDataSource.updateHero(hero.toLocal())
    override suspend fun getHeroLocations(id: String): List<LocationModel> = remoteDataSource.getLocationsHero(id).map{it.toDomain()}

}