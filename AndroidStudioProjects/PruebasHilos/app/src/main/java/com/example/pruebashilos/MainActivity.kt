package com.example.pruebashilos

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pruebashilos.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
        private lateinit var coroutine:Job
    lateinit var animacion:AnimationDrawable
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
        with(binding){
            btToast.setOnClickListener({
                Toast.makeText(applicationContext, "Hola", Toast.LENGTH_SHORT).show()
            })
            btSinHilos.setOnClickListener({
                pB1.max=100
                pB1.progress = 0
                for ( i in 1..10){
                    tareaLarga()
                    pB1.progress=i*10
                }
                Toast.makeText(applicationContext, "Tarea finalizada", Toast.LENGTH_LONG).show()
                myTV.text="Tarea finalizada"
            })
            btConHilos.setOnClickListener({
                coroutine=lifecycleScope.launch {
                    var coroutine2= lifecycleScope.launch {
                        tarea10sKotlin()
                        myTV.text="Hilo hijo finalizado"
                    }
                    pB1.max=100
                    pB1.progress = 0
                    for ( i in 1..10){
                        tareaLargaKotlin()
                        pB1.progress=i*10
                    }
                    Toast.makeText(applicationContext, "Tarea finalizada", Toast.LENGTH_LONG).show()
                    myTV.text="Tarea finalizada"
                }
                myTV.text="Tarea en curso"
            })
            btStop.setOnClickListener({
                coroutine.cancel()
                myTV.text="Tarea cancelada"
            })
        }

    }
    private fun tareaLarga(){
        Thread.sleep(1000)
    }
    suspend fun tareaLargaKotlin(){
        delay(1000)
    }
    suspend fun tarea10sKotlin(){
        delay(15000)
    }
}