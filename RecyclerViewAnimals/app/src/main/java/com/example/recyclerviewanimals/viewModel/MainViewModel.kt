package com.example.recyclerviewanimals.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewanimals.model.MainState
import com.example.recyclerviewanimals.model.MyData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _datos= MutableLiveData<List<String>>(emptyList())
    val datos: LiveData<List<String>> get()=_datos
    val myEstado=MainState()
    private val _borrado: MutableLiveData<MyData> = MutableLiveData<MyData>()
    val delete:LiveData<MyData> get() = _borrado
    private val _aniadido: MutableLiveData<MyData> = MutableLiveData<MyData>()
    val add:LiveData<MyData> get() = _aniadido

    fun devuelveArray(){
        viewModelScope.launch {
            var retornoDatos=myEstado.devuelveArray()
            _datos.value = retornoDatos
        }
    }
    fun borrar(position:Int){
        viewModelScope.launch {
            _borrado.value= myEstado.borrar(position)
        }
    }

    fun aniadir(position: Int, animal:String){
        viewModelScope.launch {
            _aniadido.value=myEstado.aniadir(position, animal)
        }
    }
}