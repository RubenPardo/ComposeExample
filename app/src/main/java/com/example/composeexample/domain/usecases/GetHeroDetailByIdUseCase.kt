package com.example.composeexample.domain.usecases

import com.example.composeexample.data.repositories.interfaces.DragonBallRepository

class GetHeroDetailByIdUseCase(
    private val dragonBallRepository: DragonBallRepository
) {

    suspend fun invoke(id:String) = dragonBallRepository.getHeroById(id)
}