package com.example.composeexample.presentation.listHeroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.Response
import com.example.composeexample.domain.usecases.GetAllHeroesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val getAllHeroesUseCase: GetAllHeroesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HeroListUiState>(HeroListUiState.InitState)
    val uiState: StateFlow<HeroListUiState> = _uiState


    fun initialize() = viewModelScope.launch(Dispatchers.IO) {
        getHeroes()
    }

    private suspend fun getHeroes() {

        _uiState.emit(HeroListUiState.Loading)

        when(val response = getAllHeroesUseCase.invoke()){
            is Response.Error -> _uiState.emit(HeroListUiState.Error(response.message!!))
            is Response.Success ->_uiState.emit( HeroListUiState.Loaded(response.data!!))
        }


    }


}

sealed class HeroListUiState{
    object InitState:HeroListUiState()
    object Loading:HeroListUiState()
    data class Error(val messageError:String):HeroListUiState()
    data class Loaded(val heroes:List<Hero>):HeroListUiState()
}