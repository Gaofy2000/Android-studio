package com.example.proyectofinalmoviles.shoppingCart

import com.example.proyectofinalmoviles.entities.CartProduct

data class ShoppingCartProduct(val status:String, val shoppingCartProducts: List<CartProduct>?, val totalQuantity: Int, val totalCartPrice: Double)
