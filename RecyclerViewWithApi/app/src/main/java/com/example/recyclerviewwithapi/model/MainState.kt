package com.example.recyclerviewwithapi.model

import com.example.recyclerviewwithapi.DogRespuesta
import com.example.recyclerviewwithapi.DogsAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {
    var cadena = "https://dog.ceo/api/breed/"
    suspend fun recuperarFotos(raza: String): DogRespuesta {
        val cadenaFinal = cadena + raza + "/"
        val retrofit = Retrofit.Builder()
            .baseUrl(cadenaFinal)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val call = retrofit.create(DogsAPIService::class.java).getFotosPerros()
        val fotosPerros = call.body()
        if (fotosPerros != null) {
            return fotosPerros
        } else {
            return DogRespuesta("no success", null)
        }
    }
}