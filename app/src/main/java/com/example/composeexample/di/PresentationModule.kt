package com.example.composeexample.di

//import com.example.composeexample.presentation.herodetails.HeroDetailsViewModel
//import com.example.composeexample.presentation.herolist.HeroListViewModel
//import com.example.composeexample.presentation.login.LoginViewModel
import com.example.composeexample.presentation.detailhero.HeroDetailsViewModel
import com.example.composeexample.presentation.listHeroes.HeroListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module{
    //viewModel{ LoginViewModel(get(),get()) }
    //viewModelOf(::LoginViewModel)
    viewModel{ HeroListViewModel(get()) }
    viewModelOf(::HeroListViewModel)
    viewModel{ HeroDetailsViewModel(get(),get(), get(),get(),get()) }
    viewModelOf(::HeroDetailsViewModel)
}