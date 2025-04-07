package com.example.practicaquizmatematico2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var txtRandom: TextView
    lateinit var check2: CheckBox
    lateinit var check3: CheckBox
    lateinit var check5: CheckBox
    lateinit var check10: CheckBox
    lateinit var check0: CheckBox
    lateinit var txtRes:TextView
    var numRandom = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtRandom = findViewById(R.id.txtRandom)
        check2 = findViewById(R.id.check2)
        check3 = findViewById(R.id.check3)
        check5 = findViewById(R.id.check5)
        check10 = findViewById(R.id.check10)
        check0 = findViewById(R.id.check0)
        txtRes=findViewById(R.id.txtResultado)
    }

    fun generearNumero(v: View) {
        numRandom = (1000..2000).random()
        txtRandom.text = numRandom.toString()
    }

    fun comprobar(v: View) {
        var correcto = true
        if (check0.isChecked) {
            if (numRandom % 10 == 0 || numRandom % 5 == 0 || numRandom % 3 == 0 || numRandom % 2 == 2) {
                correcto = false
            }
        }
        if (check2.isChecked) {
            if (numRandom % 2 != 0) {
                correcto = false
            }
        }
        if (check3.isChecked) {
            if (numRandom % 3 != 0) {
                correcto = false
            }
        }
        if (check5.isChecked) {
            if (numRandom % 5 != 0) {
                correcto = false
            }
        }
        if (check10.isChecked) {
            if (numRandom % 10 != 0) {
                correcto = false
            }
        }
        if (!check2.isChecked&&!check3.isChecked&&!check5.isChecked&&!check10.isChecked&&!check0.isChecked){
            txtRes.setTextColor(Color.RED)
            txtRes.text="Debes elegir una de las opciones"
        }
        if (numRandom==0){
            txtRes.setTextColor(Color.RED)
            txtRes.text="Debes generar un numero aleatorio primero"
        }
        if (!correcto){
            txtRes.setTextColor(Color.RED)
            txtRes.text="Error"
        }
        else{
            txtRes.setTextColor(Color.GREEN)
            txtRes.text="Correcto"
        }

    }
}