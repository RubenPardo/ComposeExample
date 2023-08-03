package com.example.composeexample.data.repositories.interfaces

import com.example.composeexample.domain.model.LoginDataDO
import com.example.composeexample.domain.model.Response

interface AuthRepository {
    suspend fun saveCredentials(loginDataDO: LoginDataDO): Response<Boolean>
    suspend fun makeLogin(loginDataDO: LoginDataDO): Response<String>
    suspend fun getCredentials(): Response<LoginDataDO?>
    suspend fun removeCredentials()
    suspend fun saveToken(token: String?)
    suspend fun getToken(): Response<String?>
}
