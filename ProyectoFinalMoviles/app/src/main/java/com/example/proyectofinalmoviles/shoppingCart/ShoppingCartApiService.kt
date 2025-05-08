package com.example.proyectofinalmoviles.shoppingCart

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ShoppingCartApiService {

    @GET("cart")
    suspend fun getShoppingCart(@Header("Authorization") auth:String): Response<ShoppingCartProduct>

    @POST("cart/{productId}")
    suspend fun addProduct(@Header("Authorization") auth:String, @Path("productId") productId:Long):Response<ShoppingCartProduct>

    @POST("cart/{prodcutId}/{quantity}")
    suspend fun addManyProduct(@Header("Authorization") auth:String, @Path("productId") productId:Long, @Path("quantity") quantity: Int):Response<ShoppingCartProduct>

    @DELETE("cart/{shoppingCartProductId}")
    suspend fun deleteOneProduct(@Header("Authorization") auth:String, @Path("shoppingCartProductId") shoppingCartProductId:Long):Response<ShoppingCartProduct>

}