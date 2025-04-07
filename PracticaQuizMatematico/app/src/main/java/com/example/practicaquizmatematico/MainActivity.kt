package com.example.practicaquizmatematico

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var switchCambioColor: Switch
    lateinit var txtRandom: TextView
    lateinit var rdSi:RadioButton
    lateinit var rdNo:RadioButton
    lateinit var txtAcieto:TextView
    lateinit var fondo:ConstraintLayout

    var numRandom=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        switchCambioColor=findViewById(R.id.cambioColor)
        txtRandom=findViewById(R.id.numRandom)
        rdSi=findViewById(R.id.radioSI)
        rdNo=findViewById(R.id.radioNo)
        txtAcieto=findViewById(R.id.txtAcierto)
        fondo=findViewById(R.id.main)
    }
    fun cambiarColor(v:View){
        if (switchCambioColor.isChecked){
            fondo.setBackgroundColor(Color.YELLOW)
        }
        else{
            fondo.setBackgroundColor(Color.WHITE)
        }
    }
    fun generearNumero(v:View){
        numRandom=(1900..2500).random()
        txtRandom.text= numRandom.toString()
    }
    fun comprobar(v:View){
        if(numRandom!=0){
            if (rdSi.isChecked){
                if (numRandom%4==0){
                    txtAcieto.setTextColor(Color.GREEN)
                    txtAcieto.text="Acertastes"
                }
                else{
                    txtAcieto.setTextColor(Color.RED)
                    txtAcieto.text="Fallastes"
                }
            }
            else if(rdNo.isChecked){
                if (numRandom%4==0){
                    txtAcieto.setTextColor(Color.RED)
                    txtAcieto.text="Fallastes"
                }
                else{
                    txtAcieto.setTextColor(Color.GREEN)
                    txtAcieto.text="Acertastes"
                }
            }
            else{
                txtAcieto.text="Debes elegir una opcion"
            }
        }
        else{
            txtAcieto.text="Debes generar un numero"
        }
    }
}