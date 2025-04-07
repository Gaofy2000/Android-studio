package com.example.practicajuegodados

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.practicajuegodados.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var coroutine: Job
    private lateinit var txtNum1: TextView
    private lateinit var txtNum2: TextView
    private lateinit var txtSaldo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
        txtNum2 = findViewById(R.id.num2)
        txtNum1 = findViewById(R.id.num1)
        txtSaldo = findViewById(R.id.txtSaldo)
        var saldo: Int = 100
        var btnActivo = 0
        with(binding) {
            txtSaldo.text = saldo.toString()
            btnParImpar.setOnClickListener({
                val adapatador = ArrayAdapter.createFromResource(
                    applicationContext,
                    R.array.spinnerParImpar,
                    android.R.layout.simple_spinner_item
                )
                spinnerOpciones.adapter = adapatador
                btnActivo = 1

            })
            btnMayorMenor.setOnClickListener({
                val adapatador = ArrayAdapter.createFromResource(
                    applicationContext,
                    R.array.spinnerMayorMenor,
                    android.R.layout.simple_spinner_item
                )
                spinnerOpciones.adapter = adapatador
                btnActivo = 2
            })
            btnLanzarDados.setOnClickListener({
                if (btnActivo != 0) {
                    if (!txtApuesta.text.toString().isEmpty()) {
                        if (txtApuesta.text.toString()
                                .toInt() <= saldo
                        ) {
                            coroutine = lifecycleScope.launch {
                                var res1 = (1..6).random()
                                var res2 = (1..6).random()
                                var win = false
                                if (btnActivo == 1) {
                                    when (spinnerOpciones.selectedItemPosition) {
                                        0 -> {
                                            if ((res1 + res2) % 2 == 0) {
                                                win = true
                                                saldo += txtApuesta.text.toString().toInt()
                                            } else {
                                                saldo -= txtApuesta.text.toString().toInt()
                                            }
                                        }

                                        1 -> {
                                            if ((res1 + res2) % 2 != 0) {
                                                win = true
                                                saldo += txtApuesta.text.toString().toInt()
                                            } else {
                                                saldo -= txtApuesta.text.toString().toInt()
                                            }
                                        }
                                    }
                                }
                                if (btnActivo == 2) {
                                    when (spinnerOpciones.selectedItemPosition) {
                                        0 -> {
                                            if ((res1 + res2) >= 7) {
                                                win = true
                                                saldo += txtApuesta.text.toString().toInt()
                                            } else {
                                                saldo -= txtApuesta.text.toString().toInt()
                                            }
                                        }

                                        1 -> {
                                            if ((res1 + res2) < 7) {
                                                win = true
                                                saldo += txtApuesta.text.toString().toInt()
                                            } else {
                                                saldo -= txtApuesta.text.toString().toInt()
                                            }
                                        }
                                    }
                                }
                                lanzarDado(res1, res2, imageGif, win, btnLanzarDados,saldo)
                                txtSaldo.text = saldo.toString()
                                if (saldo == 0) {
                                    alertaSinSaldo()
                                }
                            }

                        } else {
                            alertaSaldoInsuficiente()
                        }
                    }
                    else{
                        alertaSinApuesta()
                    }

                } else {
                    alertaOpcionNoElegida()
                }
            })

        }

    }

    suspend fun lanzarDado(num1: Int, num2: Int, imageGif: ImageView, win: Boolean, btnLanzar:Button, saldo:Int) {
        btnLanzar.isEnabled=false
        Glide.with(applicationContext).load(R.drawable.dado_imagen_animada_0092)
            .into(imageGif);
        delay(3000)
        txtNum1.text = num1.toString()
        txtNum2.text = num2.toString()
        if (!win) {
            imageGif.setImageResource(R.drawable.perder_dados)
        } else {
            imageGif.setImageResource(R.drawable.ganar_dados)
        }
        delay(1000)
        if (saldo>0){
        alerta()
        }
        btnLanzar.isEnabled=true

    }

    fun alerta() {
        var myAlert = AlertDialog.Builder(this)
        myAlert.setTitle("Jugando a los dados")
        myAlert.setMessage("Desea seguir jugando?")
        myAlert.setPositiveButton("Seguir jugando", null)
        myAlert.setNegativeButton(
            "Dejar de jugar",
            DialogInterface.OnClickListener({ dialog, which ->
                this.finish()
            })
        )

        myAlert.create().show()
    }

    fun alertaOpcionNoElegida() {
        Toast.makeText(this, "Opcion no elegida", Toast.LENGTH_LONG).show()
    }

    fun alertaSaldoInsuficiente() {
        Toast.makeText(this, "Saldo insuficiente para hacer la apuesta", Toast.LENGTH_LONG).show()
    }

    fun alertaSinApuesta(){
        Toast.makeText(this, "Debe realizar una puesta", Toast.LENGTH_LONG).show()
    }

    fun alertaSinSaldo() {
        var imagen = ImageView(this)
        imagen.setImageResource(R.drawable.arruinado)
        var myAlert = AlertDialog.Builder(this)
        myAlert.setTitle("Jugando a los dados")
        myAlert.setMessage("Te has arruinado apostando debes salir del juego")
        myAlert.setView(imagen)
        myAlert.setNegativeButton(
            "Salir del juego",
            DialogInterface.OnClickListener({ dialog, which ->
                this.finish()
            })
        )
        myAlert.create().show()
    }

}