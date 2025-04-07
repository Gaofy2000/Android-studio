package com.example.abrirotrasactividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.abrirotrasactividades.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
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
            btn1.setOnClickListener {
                var myIntent: Intent= Intent(this@MainActivity, Actividad2::class.java)
                myIntent.putExtra("Texto", editTextText.text.toString())
                myActivityResultLauncher.launch(myIntent)
            }
        }
        myActivityResultLauncher= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if (result!!.resultCode== Activity.RESULT_OK){
                val miIntentResult= result.data
                findViewById<TextView>(R.id.textViewAc1).text= miIntentResult!!.extras!!.getString("mensajeBack")
            }
            else{
                Toast.makeText(this,"Ha fallado la vuelta", Toast.LENGTH_LONG).show()
            }

        }
    }
}