package com.example.pruebamensajes

import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var texto: TextView
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var imagenGif:ImageView
    lateinit var animacion:AnimationDrawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        texto = findViewById(R.id.textView)
        coordinatorLayout=findViewById((R.id.coordinador))
        imagenGif=findViewById(R.id.imageView)
        Glide.with(this).load(R.drawable.dado_imagen_animada_0092).into(imagenGif);
        imagenGif.setBackgroundResource(R.drawable.tragaperras)
        animacion= imagenGif.background as AnimationDrawable
    }

    fun toast(v: View) {
        var miToast: Toast = Toast.makeText(this, "hola", Toast.LENGTH_LONG)
        miToast.show()
        animacion.start()
    }

    fun snackBar(v: View) {
        Snackbar.make(v, "Adios", Snackbar.LENGTH_SHORT)
            .setAction("accion", View.OnClickListener { texto.text="Accion de SnackBar" })
            .show()
        animacion.stop()
    }
    fun alert(v:View){
        var myAlert=AlertDialog.Builder(this)
        myAlert.setTitle("Alerta")
        myAlert.setMessage("Esta es una alerta")
        myAlert.setNegativeButton("Cancelar", DialogInterface.OnClickListener({
            dialog, which ->  this.finish()
        }))
        myAlert.setNeutralButton("Esto es un boton mucho mas largo", null)
        myAlert.setPositiveButton("OK", DialogInterface.OnClickListener({
            dialog, which ->  texto.text="Has pulsado ok en un alert"
        }))
        myAlert.create().show()
    }
    fun customAlert(v:View){
        var myAlert=AlertDialog.Builder(this)
        myAlert.setTitle("Log in")
        var miVista=layoutInflater.inflate((R.layout.login), null)
        myAlert.setView(miVista)
        myAlert.setPositiveButton("Ok", DialogInterface.OnClickListener({
            dialog, which ->  texto.text="Bienvenido "+ miVista.findViewById<EditText>(R.id.username).text
        }))
        myAlert.setNegativeButton("Cancelar", null)
        myAlert.create().show()
    }
}