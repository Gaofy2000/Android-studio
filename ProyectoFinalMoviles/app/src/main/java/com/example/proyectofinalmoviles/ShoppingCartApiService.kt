package com.example.proyectofinalmoviles

import retrofit2.Response
import retrofit2.http.GET

interface ShoppingCartApiService {

    @GET("images")
    suspend fun getShoppingCart(): Response<ShoppingCartProduct>
}