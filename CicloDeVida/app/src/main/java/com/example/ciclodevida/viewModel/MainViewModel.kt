package com.example.ciclodevida.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.ciclodevida.model.Datos
import com.example.ciclodevida.model.MainState
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    private val _datos=MutableLiveData(Datos(0,0,false))
    val datos:LiveData<Datos> get()=_datos
    val myEstado=MainState()
    fun sumar(valor:Int, misDatos:Datos){
        viewModelScope.launch {
            var retornoDatos=myEstado.sumar(valor, misDatos)
            _datos.value=retornoDatos
        }
    }
}