package com.example.proyectofinalmoviles.shoppingCart

import retrofit2.Response
import retrofit2.http.GET

interface ShoppingCartApiService {

    @GET("")
    suspend fun getShoppingCart(): Response<ShoppingCartProduct>
}