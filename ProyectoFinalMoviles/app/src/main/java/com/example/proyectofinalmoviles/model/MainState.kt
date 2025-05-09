package com.example.proyectofinalmoviles.model

import com.example.proyectofinalmoviles.login.LoginApiService
import com.example.proyectofinalmoviles.login.LoginRequest
import com.example.proyectofinalmoviles.login.TokenDto
import com.example.proyectofinalmoviles.product.ProductApiService
import com.example.proyectofinalmoviles.product.ResponseProduct
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartApiService
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {
    var cadena = "http://10.0.2.2:8000/api/app/v1/"
    var cadenaLogin="http://10.0.2.2:8000/api/v1/"

    //Productos
    suspend fun returnAllProducts(auth:String): ResponseProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProducts(auth).body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByName(auth:String, search: String): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByName(auth, search).body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByCategory(auth:String, cat: Long): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByCategory(auth, cat).body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByAll(auth:String, search: String, cat: Long): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByAll(auth, search, cat).body() ?: ResponseProduct()
    }

    fun scrollProducts(responseProduct: ResponseProduct){
        responseProduct.number += 1
    }

    //Carrito
    suspend fun returnAllCart(auth: String): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.getShoppingCart(auth).body() ?: ShoppingCartProduct()
    }

    suspend fun addProductToCart(auth:String, productId: Long): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.addProduct(auth, productId).body() ?: ShoppingCartProduct()
    }

    suspend fun addManyProductToCart(auth:String, productId: Long, quantity: Int): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.addManyProduct(auth, productId, quantity).body() ?: ShoppingCartProduct()
    }

    suspend fun deleteOneProductFromCart(auth:String, shoppingCartProductId: Long): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.deleteOneProduct(auth, shoppingCartProductId).body() ?: ShoppingCartProduct()
    }

    //Login
    suspend fun login(loginRequest: LoginRequest): Response<TokenDto> {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadenaLogin)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginApiService::class.java).login(loginRequest)

        return service
    }
}

