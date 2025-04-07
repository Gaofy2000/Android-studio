package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(){
    lateinit var spinnerTipo:Spinner
    lateinit var spinner2:Spinner
    lateinit var imagen:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imagen=findViewById(R.id.imageView)
        spinnerTipo=findViewById(R.id.tipo)
        spinnerTipo.onItemSelectedListener= object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0->{

                    }
                    1->{
                        val adapatador= ArrayAdapter.createFromResource(applicationContext,  R.array.spinnerSeries, android.R.layout.simple_spinner_item)
                        spinner2.adapter=adapatador

                    }
                    2->{
                        val adapatador= ArrayAdapter.createFromResource(applicationContext, R.array.spinnerGrupos, android.R.layout.simple_spinner_item)
                        spinner2.adapter=adapatador
                    }
                    3->{
                        val adapatador= ArrayAdapter.createFromResource(applicationContext, R.array.spinnerAnime, android.R.layout.simple_spinner_item)
                        spinner2.adapter=adapatador

                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner2=findViewById(R.id.spinner2)
        spinner2.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(spinnerTipo.selectedItemPosition){
                    1->{
                        when(position){
                            0->{
                                imagen.setImageResource(R.drawable.himym)
                            }
                            1->{
                                imagen.setImageResource(R.drawable.friends)
                            }
                            2->{
                                imagen.setImageResource(R.drawable.breakingbad)
                            }
                        }
                    }
                    2->{
                        when(position){
                            0->{
                                imagen.setImageResource(R.drawable.twentyonepilots)
                            }
                            1->{
                                imagen.setImageResource(R.drawable.imaginedragons)
                            }
                            2->{
                                imagen.setImageResource(R.drawable.onerepublic)
                            }
                        }
                    }
                    3->{
                    when(position){
                        0->{
                            imagen.setImageResource(R.drawable.dragonball)
                        }
                        1->{
                            imagen.setImageResource(R.drawable.one_piece)
                        }
                        2->{
                            imagen.setImageResource(R.drawable.pokemon)
                        }
                    }
                }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

}