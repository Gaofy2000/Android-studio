package com.example.proyectofinalmoviles.shoppingCart.model

import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartApiService
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {
    var cadena = "http://10.0.2.2:8000/api/v1/"
    suspend fun recuperarSCProductos(url: String): ShoppingCartProduct {
        val cadenaFinal = cadena + url
        val retrofit = Retrofit.Builder()
            .baseUrl(cadenaFinal)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val call = retrofit.create(ShoppingCartApiService::class.java).getShoppingCart()
        val productos = call.body()
        if (productos != null) {
            return productos
        } else {
            return ShoppingCartProduct("no success", null, 0 ,0.0 )
        }
    }
}
