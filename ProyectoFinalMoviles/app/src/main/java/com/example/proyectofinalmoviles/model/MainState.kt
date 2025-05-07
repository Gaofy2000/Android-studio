package com.example.proyectofinalmoviles.model

import com.example.proyectofinalmoviles.product.ProductApiService
import com.example.proyectofinalmoviles.product.ResponseProduct
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartApiService
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {
    var cadena = "http://10.0.2.2:8000/api/app/v1/"

    //Productos
    suspend fun returnAllProducts(): ResponseProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProducts().body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByName(search: String): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByName(search).body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByCategory(cat: Long): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByCategory(cat).body() ?: ResponseProduct()
    }

    suspend fun returnAllProductsByAll(search: String, cat: Long): ResponseProduct{
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductApiService::class.java)

        return service.getAllProductsByAll(search, cat).body() ?: ResponseProduct()
    }

    //Carrito
    suspend fun returnAllCart(): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.getShoppingCart().body() ?: ShoppingCartProduct()
    }

    suspend fun addProductToCart(productId: Long): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.addProduct(productId).body() ?: ShoppingCartProduct()
    }

    suspend fun addManyProductToCart(productId: Long, quantity: Int): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.addManyProduct(productId, quantity).body() ?: ShoppingCartProduct()
    }

    suspend fun deleteOneProductFromCart(shoppingCartProductId: Long): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.deleteOneProduct(shoppingCartProductId).body() ?: ShoppingCartProduct()
    }

    suspend fun deleteAllProductsFromCart(): ShoppingCartProduct {
        val retrofit = Retrofit.Builder()
            .baseUrl(cadena)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ShoppingCartApiService::class.java)

        return service.deleteAllProducts().body() ?: ShoppingCartProduct()
    }
}