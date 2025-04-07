package com.example.recycleviewcolores.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recycleviewcolores.Color
import com.example.recycleviewcolores.model.MainState
import com.example.recycleviewcolores.model.MyData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _datos= MutableLiveData<List<Color>>(emptyList())
    val datos: LiveData<List<Color>> get()=_datos
    val myEstado=MainState()
    private val _borrado: MutableLiveData<MyData> = MutableLiveData<MyData>()
    val delete:LiveData<MyData> get() = _borrado
    private val _aniadido: MutableLiveData<MyData> = MutableLiveData<MyData>()
    val add:LiveData<MyData> get() = _aniadido
    fun devuelveArray(){
        viewModelScope.launch {
            var retornoDatos=myEstado.devolverArray()
            _datos.value = retornoDatos
        }
    }
    fun borrar(position:Int){
        viewModelScope.launch {
            _borrado.value= myEstado.borrar(position)
        }
    }

    fun aniadir(position: Int, color: Color){
        viewModelScope.launch {
            _aniadido.value=myEstado.aniadir(position, color)
        }
    }
}