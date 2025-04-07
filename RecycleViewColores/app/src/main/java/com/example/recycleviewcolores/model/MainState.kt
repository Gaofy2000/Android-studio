package com.example.recycleviewcolores.model

import com.example.recycleviewcolores.Color

private var colores= mutableListOf(Color("Verde", "#ff4caf50"),
    Color("Amarillo", "#FFEB3B"),
    Color("Azul", "#ff2196f3"),
    Color("Indigo","#ff3f51b5"),
    Color("Rojo", "#fff44336"),
    Color("Naranja", "#ff6f00"),
    Color("Gris", "#757575"),
    Color("Violeta", "#ff673ab7"))

class   MainState {
    fun devolverArray():List<Color>{
        return colores
    }
    fun borrar(position:Int):MyData{
        colores.removeAt(position)
        return MyData(position, colores)
    }
    fun aniadir(position: Int, color: Color): MyData{
        colores.add(position, color)
        return MyData(position, colores)
    }
}