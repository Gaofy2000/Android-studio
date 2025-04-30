package com.example.proyectofinalmoviles.shoppingCart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import com.example.proyectofinalmoviles.shoppingCart.model.MainState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
    val myEstado= MainState()
    private val _datos= MutableLiveData<ShoppingCartProduct>(ShoppingCartProduct(null.toString(), ArrayList(), 0, 0.0))
    val datos: LiveData<ShoppingCartProduct> get()= _datos

    fun devuelveSCProductos(url:String){
        viewModelScope.launch {
            _datos.value= myEstado.recuperarSCProductos(url)
        }
    }
}