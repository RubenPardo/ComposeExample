package com.example.composeexample.data.repositories

import android.util.Log
import com.example.composeexample.data.datasources.remote.interfaces.AuthService
import com.example.composeexample.data.datasources.remote.interfaces.SharedPreferencesService
import com.example.composeexample.data.repositories.interfaces.AuthRepository
import com.example.composeexample.domain.model.LoginDataDO
import com.example.composeexample.domain.model.Response



class AuthRepositoryImpl(
    private val sharedPreferencesService: SharedPreferencesService,
    private val authService: AuthService
): AuthRepository {

    private val loginDataKEY = "LOGIN"
    private val tokenDataKEY = "TOKEN"

    override suspend fun saveCredentials( loginDataDO: LoginDataDO): Response<Boolean> {
        return try {
            sharedPreferencesService.putPrefString( loginDataKEY, loginDataDO.toJson())
            Response.Success(true)
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun makeLogin(loginDataDO: LoginDataDO): Response<String> {

        val response = authService.login(loginDataDO)
       try {
           return when(response){
               is Response.Error -> Response.Error(response.message)
               is Response.Success -> {
                   response.data?.let {
                       return Response.Success(it)
                   } ?: return Response.Error("Unable to login")
               }
           }
       }catch (e:Exception){
           Log.e("LOGIN",e.message.toString())
           return Response.Error("Unable to login")
       }
    }

    override suspend fun getCredentials(): Response<LoginDataDO?> {
        return try {
            val loginDataStr = sharedPreferencesService.getPrefString(loginDataKEY,"")
            if(loginDataStr!=null && loginDataStr != ""){
                Response.Success(LoginDataDO.fromJson(loginDataStr))
            }else{
                Response.Success(null)
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }

    override suspend fun removeCredentials() {
        sharedPreferencesService.removePrefString(loginDataKEY)

    }

    override suspend fun saveToken(token: String?) {
        Log.d("TOKEN",token.toString())
        sharedPreferencesService.putPrefString( tokenDataKEY, token!!)
    }

    override suspend fun getToken():Response<String?> {
        return try {
            val token = sharedPreferencesService.getPrefString(tokenDataKEY,"")
            if(token!=null && token != ""){
                Response.Success(token)
            }else{
                Response.Error("No token")
            }
        }catch (e:Exception){
            Response.Error(e.message)
        }
    }


}