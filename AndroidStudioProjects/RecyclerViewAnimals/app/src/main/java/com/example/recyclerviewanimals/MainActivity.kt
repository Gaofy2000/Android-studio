package com.example.recyclerviewanimals

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewanimals.databinding.ActivityMainBinding
import com.example.recyclerviewanimals.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MainViewModel by viewModels()
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding) {
            myViewModel.devuelveArray()
            val mLayout = LinearLayoutManager(this@MainActivity)
            rvAnimales.layoutManager = mLayout
            myViewModel.datos.observe(this@MainActivity) {
                myAdapter = MyAdapter(it)
                rvAnimales.adapter = myAdapter
                val miDividerItemDeclaration =
                    DividerItemDecoration(
                        rvAnimales.context, mLayout.orientation
                    )
                rvAnimales.addItemDecoration(miDividerItemDeclaration)
            }
            btnDelete.setOnClickListener {
                if (myAdapter.positionClicked < 0) {
                    Toast.makeText(
                        applicationContext,
                        "Debe seleccionar una fila",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                myViewModel.borrar(myAdapter.positionClicked)
            }
            btnAdd.setOnClickListener {
                var position=myAdapter.positionClicked
                if (myAdapter.positionClicked < 0) {
                    position=0
                }
                myViewModel.aniadir(position, txtAddAnimal.text.toString())
            }
            myViewModel.delete.observe(this@MainActivity) {
                myAdapter.notifyItemRemoved(it.position)
                myAdapter.positionClicked = RecyclerView.NO_POSITION
                myAdapter.notifyItemRangeChanged(0, it.animal.size)
            }
            myViewModel.add.observe(this@MainActivity){
                myAdapter.notifyItemInserted(it.position)
                myAdapter.positionClicked = RecyclerView.NO_POSITION
                myAdapter.notifyItemRangeChanged(0, it.animal.size)
            }
        }

    }
}