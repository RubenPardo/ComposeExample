package com.example.composeexample.domain.usecases

import com.example.composeexample.data.repositories.interfaces.DragonBallRepository
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.Response
import java.lang.Exception

class SetHeroFavByIdUseCase(
    private val dragonBallRepository: DragonBallRepository
) {


    suspend fun invoke(hero:Hero, isFav:Boolean): Response<Hero>{
        val newHero = hero.copy(favorite = isFav)
        return if( dragonBallRepository.updateHero(hero)){
            Response.Success(newHero)
        }else{
            Response.Error("No se pudo realizar la accion")
        }
    }
}