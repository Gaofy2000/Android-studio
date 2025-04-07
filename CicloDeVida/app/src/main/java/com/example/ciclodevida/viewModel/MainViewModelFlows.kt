package com.example.ciclodevida.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ciclodevida.model.Datos
import com.example.ciclodevida.model.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModelFlows: ViewModel(){
    private val _datos= MutableStateFlow(Datos(0,0,false))
    val datos: StateFlow<Datos> get()=_datos.asStateFlow()
    val myEstado= MainState()
    fun sumar(valor:Int, misDatos: Datos){
        viewModelScope.launch {
            var retornoDatos=myEstado.sumar(valor, misDatos)
            _datos.value=retornoDatos
        }
    }
}