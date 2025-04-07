package com.example.menu_hamburguesa

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.menu_hamburguesa.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    lateinit var materialToolbar: MaterialToolbar




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

//        materialToolbar=findViewById(R.id.materialToolbar)

        setSupportActionBar(binding.materialToolbar)

        val toogle = ActionBarDrawerToggle(
            this, binding.main, binding.materialToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.main.addDrawerListener(toogle)
        toogle.syncState()

        binding.navview.setNavigationItemSelectedListener{
            if(it.itemId==R.id.id_inicio){
                val myfragmentmanager : FragmentManager = supportFragmentManager
                val myTransactionFragment : FragmentTransaction = myfragmentmanager.beginTransaction()
                val myFragment : web_fragment = web_fragment.newInstance("https://iesclaradelrey.es/portal/index.php/es/")
                myTransactionFragment.add(R.id.myLinearL, myFragment)
                    .commit()
            }
            true
        }

//        onBackPressedDispatcher.addCallback{
//            if (binding.main.isDrawerOpen()) {
//
//            }
//        }



    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        TODO("Not yet implemented")
//    }
}