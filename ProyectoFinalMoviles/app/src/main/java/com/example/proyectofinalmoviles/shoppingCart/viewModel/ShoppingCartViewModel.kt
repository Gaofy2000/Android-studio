package com.example.proyectofinalmoviles.shoppingCart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmoviles.model.MainState
import com.example.proyectofinalmoviles.shoppingCart.ShoppingCartProduct
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel(){
    val myEstado= MainState()
    private val _datos= MutableLiveData<ShoppingCartProduct>(ShoppingCartProduct())
    val datos: LiveData<ShoppingCartProduct> get()= _datos
    private val _addToCartResult = MutableLiveData<Boolean>()
    val addToCartResult: LiveData<Boolean> get() = _addToCartResult
    private val _deleteFromCartResult = MutableLiveData<Boolean>()
    val deleteFromCartResult: LiveData<Boolean> get() = _deleteFromCartResult

    fun returnAllCart(){
        viewModelScope.launch {
            _datos.value= myEstado.returnAllCart()
        }
    }
    fun addProductToCart(productId: Long, quantity: Int = 1) {
        viewModelScope.launch {
            try {
                if (quantity > 1) {
                    val response = myEstado.addManyProductToCart(productId, quantity)
                    _datos.value = response
                    _addToCartResult.value = true
                } else {
                    val response = myEstado.addProductToCart(productId)
                    _datos.value = response
                    _addToCartResult.value = true
                }
            } catch (e: Exception) {
                _addToCartResult.value = false
            }
        }
    }

    fun deleteProductFromCart(shoppingCartProductId: Long) {
        viewModelScope.launch {
            try {
                val response = myEstado.deleteOneProductFromCart(shoppingCartProductId)
                _datos.value = response
                _deleteFromCartResult.value = true
            } catch (e: Exception) {
                _deleteFromCartResult.value = false
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            try {
                val response = myEstado.deleteAllProductsFromCart()
                _datos.value = response
                _deleteFromCartResult.value = true
            } catch (e: Exception) {
                _deleteFromCartResult.value = false
            }
        }
    }
}