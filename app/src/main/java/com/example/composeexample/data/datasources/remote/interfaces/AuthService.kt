package com.example.composeexample.data.datasources.remote.interfaces

import com.example.composeexample.domain.model.LoginDataDO
import com.example.composeexample.domain.model.Response

interface AuthService {
    suspend fun login(loginDataDO: LoginDataDO): Response<String>
}