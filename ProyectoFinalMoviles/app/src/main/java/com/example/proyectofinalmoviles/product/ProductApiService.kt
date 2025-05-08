package com.example.proyectofinalmoviles.product

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductApiService {

    @GET("products/find")
    suspend fun getAllProducts(@Header("Authorization") auth:String): Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByName(@Header("Authorization") auth:String, @Query ("search") search:String):Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByCategory(@Header("Authorizatio") auth:String, @Query("cat") cat: Long):Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByAll(@Header("Authorization") auth:String, @Query("search") search: String, @Query("cat") cat: Long):Response<ResponseProduct>
}