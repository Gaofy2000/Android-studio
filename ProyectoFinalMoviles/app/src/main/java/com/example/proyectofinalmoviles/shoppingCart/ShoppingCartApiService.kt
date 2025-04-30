package com.example.proyectofinalmoviles.shoppingCart

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShoppingCartApiService {

    @GET("")
    suspend fun getShoppingCart(): Response<ShoppingCartProduct>
    @POST("/{productId}")
    suspend fun addProduct(@Path("productId") productId:Long):Response<ShoppingCartProduct>
    @POST("/{prodcutId}/{quantity}")
    suspend fun addManyProduct(@Path("productId") productId:Long, @Path("quantity") quantity: Int):Response<ShoppingCartProduct>
    @DELETE("/{shoppingCartProductId}")
    suspend fun deleteOneProduct(@Path("shoppingCartProductId") shoppingCartProductId:Long):Response<ShoppingCartProduct>
    @DELETE("")
    suspend fun deleteAllProducts():Response<ShoppingCartProduct>
}