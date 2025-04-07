package com.example.menus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    lateinit var imagenViewMorfeo: ImageView
    lateinit var textViewMorfeo: TextView
    lateinit var materialToolbar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imagenViewMorfeo=findViewById(R.id.imageView)
        textViewMorfeo=findViewById(R.id.tV2)
        materialToolbar= findViewById(R.id.materialToolbar)
        setSupportActionBar(materialToolbar)
        supportActionBar!!.hide()
        imagenViewMorfeo.setOnClickListener{
            supportActionBar!!.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.first_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.red){
            textViewMorfeo.text= "eres un mierdas"
        }
        else if(item.itemId == R.id.blue){
            textViewMorfeo.text= "eres feliz"
        }
        else{
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
}