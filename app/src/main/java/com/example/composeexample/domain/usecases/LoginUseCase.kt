package com.example.composeexample.domain.usecases

import com.example.composeexample.data.repositories.interfaces.AuthRepository
import com.example.composeexample.domain.model.LoginDataDO
import com.example.composeexample.domain.model.Response

class LoginUseCase( private val authRepository: AuthRepository) {


    suspend operator fun invoke(
        loginDataDO: LoginDataDO,
        saveCredentials: Boolean,
    ):Boolean{
        // make login ----------------
        // if(login) ---
        return when(val response = authRepository.makeLogin(loginDataDO)){
            is Response.Error -> false
            is Response.Success -> {
                authRepository.saveToken(response.data)
                if(saveCredentials) authRepository.saveCredentials(loginDataDO)
                return true
            }
        }


    }
}