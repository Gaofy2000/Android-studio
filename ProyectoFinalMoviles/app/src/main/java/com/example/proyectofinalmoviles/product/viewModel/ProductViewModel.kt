package com.example.proyectofinalmoviles.product.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmoviles.entities.Product
import com.example.proyectofinalmoviles.login.getAuth
import com.example.proyectofinalmoviles.model.MainState
import com.example.proyectofinalmoviles.product.ResponseProduct
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    val myEstado = MainState()
    private val _datos = MutableLiveData<ResponseProduct>(ResponseProduct())
    val datos: LiveData<ResponseProduct> get() = _datos

    private var currentPage = 0
    private var currentSearch = ""
    private var currentCategory: Long = 0
    private var isSearching = false
    private var isCategoryFiltering = false

    private val accumulatedProducts = mutableListOf<Product>()

    fun returnAllProducts() {
        currentPage = 0
        currentSearch = ""
        currentCategory = 0
        isSearching = false
        isCategoryFiltering = false
        accumulatedProducts.clear()

        viewModelScope.launch {
            val response = myEstado.returnAllProducts(getAuth())
            accumulatedProducts.addAll(response.content)
            _datos.value = response
        }
    }

    fun returnALlProductsByName(search: String) {
        currentPage = 0
        currentSearch = search
        currentCategory = 0
        isSearching = true
        isCategoryFiltering = false
        accumulatedProducts.clear()

        viewModelScope.launch {
            val response = myEstado.returnAllProductsByName(getAuth(), search)
            accumulatedProducts.addAll(response.content)
            _datos.value = response
        }
    }

    fun returnAllProductsByCategory(cat: Long) {
        currentPage = 0
        currentSearch = ""
        currentCategory = cat
        isSearching = false
        isCategoryFiltering = true
        accumulatedProducts.clear()

        viewModelScope.launch {
            val response = myEstado.returnAllProductsByCategory(getAuth(), cat)
            accumulatedProducts.addAll(response.content)
            _datos.value = response
        }
    }

    fun returnAllProductsByAll(search: String, cat: Long) {
        currentPage = 0
        currentSearch = search
        currentCategory = cat
        isSearching = true
        isCategoryFiltering = true
        accumulatedProducts.clear()

        viewModelScope.launch {
            val response = myEstado.returnAllProductsByAll(getAuth(), search, cat)
            accumulatedProducts.addAll(response.content)
            _datos.value = response
        }
    }

    fun loadMoreProducts() {
        currentPage++

        viewModelScope.launch {
            val response = when {
                isSearching && isCategoryFiltering ->
                    myEstado.returnAllProductsByAll(
                        getAuth(),
                        currentSearch,
                        currentCategory,
                        currentPage
                    )

                isSearching ->
                    myEstado.returnAllProductsByName(getAuth(), currentSearch, currentPage)

                isCategoryFiltering ->
                    myEstado.returnAllProductsByCategory(getAuth(), currentCategory, currentPage)

                else ->
                    myEstado.returnAllProducts(getAuth(), currentPage)
            }

            accumulatedProducts.addAll(response.content)

            val updatedResponse = ResponseProduct(
                content = accumulatedProducts.toList(),
                pageable = response.pageable,
                last = response.last,
                totalElements = response.totalElements,
                totalPages = response.totalPages,
                size = accumulatedProducts.size,
                number = response.number,
                sort = response.sort,
                first = response.first,
                numberOfElements = accumulatedProducts.size,
                empty = accumulatedProducts.isEmpty()
            )

            _datos.value = updatedResponse
        }
    }

    fun hasMorePages(): Boolean {
        return _datos.value?.last == false
    }
}