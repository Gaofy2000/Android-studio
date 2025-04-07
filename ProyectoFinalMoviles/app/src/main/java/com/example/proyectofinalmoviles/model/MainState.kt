/*
package com.example.proyectofinalmoviles.model

class MainState {
    var cadena = "https://localhost:8000"
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
}*/
