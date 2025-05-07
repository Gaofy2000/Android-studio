package com.example.proyectofinalmoviles.product

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {

    @GET("products/find")
    suspend fun getAllProducts(): Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByName(@Query ("search") search:String):Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByCategory(@Query("cat") cat: Long):Response<ResponseProduct>

    @GET("products/find")
    suspend fun getAllProductsByAll(@Query("search") search: String, @Query("cat") cat: Long):Response<ResponseProduct>
}