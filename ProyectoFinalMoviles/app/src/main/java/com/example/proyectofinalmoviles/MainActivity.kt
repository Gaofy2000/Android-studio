package com.example.proyectofinalmoviles

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalmoviles.databinding.ActivityMainBinding
import com.example.proyectofinalmoviles.login.LoginRequest
import com.example.proyectofinalmoviles.login.viewModel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        with(binding){
            btnLogin.setOnClickListener {
                val username = binding.txtUserName.text.toString()
                val password = binding.txtPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val loginRequest = LoginRequest(username, password)
                    loginViewModel.login(loginRequest)
                } else {
                    Toast.makeText(applicationContext, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }
            loginViewModel.loginResult.observe(this@MainActivity) { result ->
                result.onSuccess {
                    labelError.visibility= View.GONE
                    Toast.makeText(applicationContext, "Login exitoso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MenuActivity::class.java))
                }
                result.onFailure { error ->
                    labelError.visibility= View.VISIBLE
                    Toast.makeText(applicationContext, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
