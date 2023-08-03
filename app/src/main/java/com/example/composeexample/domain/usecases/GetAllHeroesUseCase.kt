package com.example.composeexample.domain.usecases

import com.example.composeexample.data.repositories.interfaces.AuthRepository
import com.example.composeexample.data.repositories.interfaces.DragonBallRepository
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.Response

class GetAllHeroesUseCase(
    private val dragonBallRepository: DragonBallRepository,
) {


    suspend operator fun invoke():Response<List<Hero>>{

        return when(val response = dragonBallRepository.getAllHeroes("")){
            is Response.Error -> Response.Error("")
            is Response.Success -> Response.Success(response.data!!)
        }



    }
}