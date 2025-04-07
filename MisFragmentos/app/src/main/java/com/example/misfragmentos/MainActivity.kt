package com.example.misfragmentos

import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnShow = findViewById<Button>(R.id.btnLayout)
        btnShow.setOnClickListener {
            val editText: EditText= findViewById(R.id.editText)
            val myFragmentManager: FragmentManager = supportFragmentManager
            val myFragmentTransaction: FragmentTransaction = myFragmentManager.beginTransaction()
            val myFragment: FirstFragment = FirstFragment.newInstance(editText.text.toString())
            myFragmentTransaction.replace(R.id.frameForFragment, myFragment).commit()
        }
    }
}