package com.example.proyectofinalmoviles.product

import com.example.proyectofinalmoviles.entities.Pageable
import com.example.proyectofinalmoviles.entities.Product
import com.example.proyectofinalmoviles.entities.Sort

data class ResponseProduct(
    val content: List<Product> = emptyList(),
    val pageable: Pageable = Pageable(),
    val last: Boolean = false,
    val totalElements: Int = 0,
    val totalPages: Int = 0,
    val size: Int = 0,
    var number: Int = 0,
    val sort: Sort = Sort(),
    val first: Boolean = true,
    val numberOfElements: Int = 0,
    val empty: Boolean = true
)
