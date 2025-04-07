package com.example.aplicacionparapruebas

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    lateinit var btn: Button;
    lateinit var txt: TextView;
    lateinit var patricio:ImageView;
    lateinit var imagenAvatar: ImageButton;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btn= findViewById(R.id.button21);
        txt=findViewById(R.id.textView);
        patricio= findViewById(R.id.Patricio);
        imagenAvatar=findViewById(R.id.imageButton)
        /*btn.setOnClickListener(View.OnClickListener {
            txt.text="Hola"
            txt.setTextColor(Color.BLUE)
        })*/
        btn.setOnClickListener(this)
        btn.setOnLongClickListener(this)
        imagenAvatar.setOnClickListener(this)
        imagenAvatar.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        patricio.setImageResource(R.drawable.ic_launcher_foreground)
        txt.text="Hola"
        txt.setTextColor(Color.BLUE)
    }

    override fun onLongClick(v: View?): Boolean {
        patricio.setImageResource(R.drawable.patricio2)
        txt.text="Adios"
        txt.setTextColor(Color.RED)
        return true
    }
}