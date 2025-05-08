package com.example.proyectofinalmoviles.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenDto>
}