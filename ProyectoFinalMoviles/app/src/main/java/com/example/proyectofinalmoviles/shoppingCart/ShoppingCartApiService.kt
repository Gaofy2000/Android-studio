package com.example.proyectofinalmoviles.shoppingCart

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ShoppingCartApiService {

    @GET("")
    suspend fun getShoppingCart(): Response<ShoppingCartProduct>
    @POST("/{productId}")
    suspend fun addProduct():Response<ShoppingCartProduct>
    @POST("/{prodcutId}/{quantity}")
    suspend fun addManyProduct():Response<ShoppingCartProduct>
    @DELETE("/{shoppingCartProductId}")
    suspend fun deleteOneProduct():Response<ShoppingCartProduct>
    @DELETE("")
    suspend fun deleteAllProducts():Response<ShoppingCartProduct>
}