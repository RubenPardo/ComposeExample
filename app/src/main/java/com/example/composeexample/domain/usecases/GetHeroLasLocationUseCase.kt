package com.example.composeexample.domain.usecases

import com.example.composeexample.data.repositories.interfaces.DragonBallRepository
import com.example.composeexample.domain.model.LocationModel

class GetHeroLasLocationUseCase(
    private val dragonBallRepository: DragonBallRepository
) {

    suspend fun invoke(id:String): LocationModel?{
       val locations = dragonBallRepository.getHeroLocations(id)
        return if(locations.isNotEmpty()){
            locations.last()
        }else{
            null
        }
    }

}