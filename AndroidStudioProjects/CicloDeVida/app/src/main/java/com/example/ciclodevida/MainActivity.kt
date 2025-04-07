package com.example.ciclodevida

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ciclodevida.databinding.ActivityMainBinding
import com.example.ciclodevida.model.Datos
import com.example.ciclodevida.viewModel.MainViewModel
import com.example.ciclodevida.viewModel.MainViewModelFlows
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myModel:MainViewModel by viewModels()
    private val myViewModelFlows:MainViewModelFlows by viewModels()
    var numClicks=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
/*
        setContentView(R.layout.activity_main)
*/
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.resultado.text= "0"
        binding.btnSumar.setOnClickListener({
/*
            myModel.sumar(binding.editTextNumber.text.toString().toInt(),Datos(binding.resultado.text.toString().toInt(), numClicks, false))
*/

            myViewModelFlows.sumar(binding.editTextNumber.text.toString().toInt(), Datos(binding.resultado.text.toString().toInt(), numClicks, false))
        })
        /*myModel.datos.observe(this){
            binding.resultado.text=it.contador.toString()
            numClicks=it.numClicks
            if(it.mostrarMensaje){
                Toast.makeText(this, "Has llegado a 5 clicks", Toast.LENGTH_LONG)
            }
        }*/
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                myViewModelFlows.datos.collect{
                    binding.resultado.text=it.contador.toString();
                    numClicks=it.numClicks
                    if (it.mostrarMensaje==true){
                        Toast.makeText(applicationContext, "Has llegado a 5 clicks", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("suma", binding.resultado.text.toString())
        outState.putInt("numClicks", numClicks)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numClicks=savedInstanceState.getInt("numClicks")
        binding.resultado.text=savedInstanceState.getString("suma")
    }*/
}