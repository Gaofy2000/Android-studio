package com.example.recyclerviewanimals.model

private var animalesName= mutableListOf("Caballo", "Vaca", "Perro", "Gato", "Leon", "Tigre", "Rinoceronte", "Elefante", "Aguila", "Mariposa", "Serpiente", "Oso")

class MainState {
    fun devuelveArray():List<String>{
        return animalesName
    }
    fun borrar(position:Int):MyData{
        animalesName.removeAt(position)
        return MyData(position, animalesName)
    }
    fun aniadir(position: Int, animal:String): MyData{
        animalesName.add(position, animal)
        return MyData(position, animalesName)
    }
}