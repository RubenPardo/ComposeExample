package com.example.composeexample.presentation.listHeroes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.presentation.common_composables.CustomTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListHeroesScreen(
    viewModel: HeroListViewModel = koinViewModel(),
    goToHeroDetail: (heroId: String) -> Unit
) {
    Scaffold (
        topBar = {
            CustomTopAppBar("Hero List", backCallback = null)
        }
    ){
       it
        viewModel.initialize()
        val state = viewModel.uiState.collectAsState()

        when(state.value){
            is HeroListUiState.Loading -> LoadingBody()
            is HeroListUiState.Loaded -> HeroListBody((state.value as HeroListUiState.Loaded).heroes,goToHeroDetail)
            is HeroListUiState.Error -> {}
            HeroListUiState.InitState -> {}
        }
    }
}

@Composable
fun LoadingBody() {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun HeroListBody(heroes: List<Hero>, goToHeroDetail: (heroId: String) -> Unit) {
    LazyColumn{
        items(heroes){hero->
            HeroItem(hero, onClickCallback = {goToHeroDetail.invoke(hero.id)})
        }
    }
}


