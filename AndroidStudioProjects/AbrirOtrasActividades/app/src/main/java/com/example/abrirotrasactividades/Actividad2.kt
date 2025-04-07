package com.example.abrirotrasactividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var myExtra: Bundle? = intent.extras
        var myMessage= myExtra!!.getString("Texto")
        findViewById<TextView>(R.id.textView2).text= myMessage

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            val myResultado= Intent()
            myResultado.putExtra("mensajeBack", findViewById<EditText>(R.id.editTextTextAc2).text.toString())
            setResult(RESULT_OK, myResultado)
            this.finish()
        }


    }
}