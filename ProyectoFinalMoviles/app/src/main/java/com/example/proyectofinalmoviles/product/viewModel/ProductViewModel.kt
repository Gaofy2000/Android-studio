package com.example.proyectofinalmoviles.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmoviles.login.getAuth
import com.example.proyectofinalmoviles.model.MainState
import com.example.proyectofinalmoviles.product.ResponseProduct
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    val myEstado= MainState()
    private val _datos= MutableLiveData<ResponseProduct>(ResponseProduct())
    val datos: LiveData<ResponseProduct> get()= _datos

    fun returnAllProducts(currentPage: Int){
        viewModelScope.launch {
            _datos.value= myEstado.returnAllProducts(getAuth(), currentPage)
        }
    }

    fun returnALlProductsByName(search:String, currentPage: Int){
        viewModelScope.launch {
            _datos.value= myEstado.returnAllProductsByName(getAuth(), search, currentPage)
        }
    }

    fun returnAllProductsByCategory(cat: Long, currentPage: Int){
        viewModelScope.launch {
            _datos.value= myEstado.returnAllProductsByCategory(getAuth(), cat, currentPage)
        }
    }

    fun returnAllProductsByAll(search: String, cat: Long, currentPage: Int){
        viewModelScope.launch {
            _datos.value= myEstado.returnAllProductsByAll(getAuth(), search, cat, currentPage)
        }
    }

    /*fun scrollProducts(responseProduct: ResponseProduct){
        viewModelScope.launch {
            _datos.value = myEstado.srollProducts(responseProduct)
        }
    }*/

}
