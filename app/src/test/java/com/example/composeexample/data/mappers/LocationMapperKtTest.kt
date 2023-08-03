package com.example.composeexample.data.mappers

import com.example.composeexample.data.datasources.remote.model.HeroIdDto
import com.example.composeexample.data.datasources.remote.model.LocationDto
import com.example.composeexample.domain.model.LocationModel
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class LocationMapperKtTest{


    @Test
    fun `WHEN Map correctly Location DTO to Location Model EXPECT location model`(){

        val locationEsperado = LocationModel(-3.56,1.5)

        val locationDTO = LocationDto("1","","1.5","-3.56", HeroIdDto("1"))

        val locationModel = locationDTO.toDomain()



        assertThat(locationModel == locationEsperado,`is`(true))


    }

    @Test
    fun `WHEN Map Wrongly Location DTO to Location Model EXPECT Exception`(){



        val locationDTO = LocationDto("1","","","-3.56", HeroIdDto("1"))

        var error = false
        try {
            locationDTO.toDomain()
        }catch (e: Exception){
            error = true
        }


        assertThat(error ,`is`(true))


    }

    @Test
    fun `WHEN Map Location DTO with null lat and log to Location Model EXPECT location model lat lng with 0`(){


        val locationEsperado = LocationModel(0.0,0.0)

        val locationDTO = LocationDto("1","",null,null, HeroIdDto("1"))

        val locationModel = locationDTO.toDomain()

        assertThat(locationModel == locationEsperado,`is`(true))

    }
}