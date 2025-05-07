package com.example.proyectofinalmoviles.entities

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val price: Double,
    val categoryId: Int
)
