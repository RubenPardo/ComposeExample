package com.example.composeexample.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.composeexample.presentation.detailhero.DetailHeroScreen
import com.example.composeexample.presentation.listHeroes.ListHeroesScreen
import com.example.composeexample.presentation.login.LoginScreen

fun NavGraphBuilder.addLoginScreen(navController: NavController){
    composable(Screen.LoginScreen.route){
        LoginScreen{

            navController.navigate(Screen.HeroListScreen.route){
                /*popUpTo(Screen.LoginScreen.route){
                    inclusive = true
                }*/ // Remove the stack
            }
        }
    }
}

fun NavGraphBuilder.addHeroList(navController: NavController){
    composable(Screen.HeroListScreen.route){
        ListHeroesScreen(
            goToHeroDetail = { navController.navigate(Screen.HeroDetails.route + "/$it")},
        )
    }
}

fun NavGraphBuilder.addHeroDetails(navController: NavHostController) {
    composable(
        route = Screen.HeroDetails.route + "/{hero_id}",
        arguments = Screen.HeroDetails.arguments)
    {
        val id = it.arguments?.getString("hero_id") ?: ""
        DetailHeroScreen(
            heroId = id,
            goBack = {if(navController.previousBackStackEntry!=null) {navController.navigateUp()} else null}
        )
    }
}