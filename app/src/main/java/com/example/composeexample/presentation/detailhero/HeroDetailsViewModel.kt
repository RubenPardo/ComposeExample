package com.example.composeexample.presentation.detailhero

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.R
import com.example.composeexample.domain.model.Hero
import com.example.composeexample.domain.model.LocationModel
import com.example.composeexample.domain.model.Response
import com.example.composeexample.domain.usecases.GetDistanceFroHeroUseCase
import com.example.composeexample.domain.usecases.GetHeroDetailByIdUseCase
import com.example.composeexample.domain.usecases.GetHeroLasLocationUseCase
import com.example.composeexample.domain.usecases.SetHeroFavByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HeroDetailsViewModel(
    private val context: Context,
    private val getHeroDetailByIdUseCase: GetHeroDetailByIdUseCase,
    private val getHeroLasLocationUseCase: GetHeroLasLocationUseCase,
    private val getDistanceFroHeroUseCase: GetDistanceFroHeroUseCase,
    private val setHeroFavByIdUseCase: SetHeroFavByIdUseCase
) : ViewModel() {

    private val _heroLiveData = MutableStateFlow<Hero?>(null)
    val heroLiveData: StateFlow<Hero?> = _heroLiveData

    private val _location = MutableStateFlow<String?>(null)
    val location: StateFlow<String?> = _location

    private lateinit var heroModel:Hero

    private var userLocation: LocationModel? = null
    private var heroLocation: LocationModel? = null

    fun getData(id: String) {
        getHeroById(id)
        getLastLocation(id)
    }

    private fun getHeroById(id: String) = viewModelScope.launch (Dispatchers.IO){
        val result = getHeroDetailByIdUseCase.invoke(id)
        heroModel = result
        _heroLiveData.emit(result)
    }

    private fun showLocation(){
        userLocation?.let { userSafe ->
            heroLocation?.let { heroSafe ->
                val distance = getDistanceFroHeroUseCase.invoke(userSafe,heroSafe)
                _location.value = context.getString(R.string.user_location,heroSafe,distance.toString())
            }
        }
    }

    private fun getLastLocation(id:String){
        viewModelScope.launch (Dispatchers.IO){
            val result = getHeroLasLocationUseCase.invoke(id)
            result?.let {
                heroLocation = it
                runBlocking (Dispatchers.Main){
                    showLocation()
                }
            }


        }
    }

    fun changeFavHeroById() = viewModelScope.launch (Dispatchers.IO){
        when(val result = setHeroFavByIdUseCase.invoke(heroModel,!heroModel.favorite)){
            is Response.Error -> {}
            is Response.Success -> {
                heroModel = result.data!!
                _heroLiveData.emit(heroModel)
            }
        }
    }


    fun setUserLocation(lat:Double, long: Double) {
        userLocation = LocationModel(lat, long)
        showLocation()
    }

}