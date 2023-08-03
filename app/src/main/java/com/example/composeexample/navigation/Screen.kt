package com.example.composeexample.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route:String,
    val arguments: List<NamedNavArgument>
){
    object LoginScreen : Screen(
        route = "login",
        arguments = emptyList()
    )

    object HeroListScreen : Screen(
        route = "hero_list",
        arguments = emptyList()
    )
    object HeroDetails : Screen(
        route = "hero_list",
        arguments = listOf(navArgument("hero_id") { type = NavType.StringType })
    )
}
