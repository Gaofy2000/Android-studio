package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var n1:Double=0.0
    var n2:Double=0.0
    lateinit var num1:EditText;
    lateinit var num2:EditText;
    lateinit var res: TextView;
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        res=findViewById(R.id.resultado)
        num1=findViewById(R.id.numero1)
        num2=findViewById(R.id.numero2);
    }
    fun sumar(v: View){
        n1=num1.text.toString().toDouble();
        n2=num2.text.toString().toDouble();
        res.text= (n1+n2).toString()
    }
    fun restar(v:View){
        n1=num1.text.toString().toDouble();
        n2=num2.text.toString().toDouble();
        res.text= (n1-n2).toString();
    }
    fun dividir(v:View){
        n1=num1.text.toString().toDouble();
        n2=num2.text.toString().toDouble();
        res.text= (n1/n2).toString();
    }
    fun multiplicar(v:View){
        n1=num1.text.toString().toDouble();
        n2=num2.text.toString().toDouble();
        res.text= (n1*n2).toString();
    }
}