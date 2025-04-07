package com.example.aplicacionparapruebas2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    lateinit var imgbtn:ImageButton
    lateinit var spinner: Spinner
    lateinit var txt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        imgbtn=findViewById(R.id.imageButton)
        spinner=findViewById(R.id.spinner)
        val paises= arrayOf<String>("España", "Francia", "Italia")
        var adapatador= ArrayAdapter(this, android.R.layout.simple_spinner_item, paises)
        txt= findViewById(R.id.textView2)
        imgbtn.setOnClickListener(this)
        adapatador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapatador
        spinner.onItemSelectedListener=this
    }

    override fun onClick(v: View?) {
        Log.i("Mi primera aplicacion", "Mi primer mensaje")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        txt.text=spinner.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}