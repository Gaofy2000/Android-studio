package com.example.proyectofinalmoviles.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalmoviles.login.IncorrectLoginException
import com.example.proyectofinalmoviles.login.LoginRequest
import com.example.proyectofinalmoviles.login.accessToken
import com.example.proyectofinalmoviles.login.refreshToken
import com.example.proyectofinalmoviles.model.MainState
import kotlinx.coroutines.launch

class LoginViewModel :ViewModel(){
    val myEstado= MainState()
    private val _loginResult= MutableLiveData<Result<Boolean>>()
    val loginResult: LiveData<Result<Boolean>> get()= _loginResult
    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            try {
                val response= myEstado.login(loginRequest)
                if (response.isSuccessful&& response.body()!=null){
                    accessToken= response.body()!!.accessToken
                    refreshToken= response.body()!!.refreshToken

                    _loginResult.postValue(Result.success(true))

                }else{
                    when(response.code()){
                        403-> _loginResult.postValue(Result.failure(IncorrectLoginException("Usuario o contraseña incorrecta")))

                        else-> _loginResult.postValue(Result.failure(RuntimeException("Error ${response.code()}: ${response.message()}")))
                    }
                }
            }catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            }
        }
    }
}