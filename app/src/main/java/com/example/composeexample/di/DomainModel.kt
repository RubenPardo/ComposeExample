package com.example.composeexample.di

import com.example.composeexample.domain.usecases.GetAllHeroesUseCase
import com.example.composeexample.domain.usecases.GetDistanceFroHeroUseCase
import com.example.composeexample.domain.usecases.GetHeroDetailByIdUseCase
import com.example.composeexample.domain.usecases.GetHeroLasLocationUseCase
import com.example.composeexample.domain.usecases.LoginUseCase
import com.example.composeexample.domain.usecases.SetHeroFavByIdUseCase
import org.koin.dsl.module

val domainModel = module{

    factory {GetAllHeroesUseCase(get())}
    factory {LoginUseCase(get())}
    factory {GetHeroDetailByIdUseCase(get())}
    factory {SetHeroFavByIdUseCase(get())}
    factory {GetHeroLasLocationUseCase(get())}
    factory {GetDistanceFroHeroUseCase()}


}