package com.example.proyectofinalmoviles

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.proyectofinalmoviles.databinding.ActivityMainBinding
import com.example.proyectofinalmoviles.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            setSupportActionBar(myToolbar)
            val toggle= ActionBarDrawerToggle(this@MenuActivity, main, myToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
            main.addDrawerListener(toggle)
            toggle.syncState()
            val myFragmentManager: FragmentManager = supportFragmentManager
            val myFragmentTransaction: FragmentTransaction = myFragmentManager.beginTransaction()
            val myFragment: WebFragment = WebFragment.newInstance("https://iesclaradelrey.es/portal/index.php/es/")
            myFragmentTransaction.replace(R.id.web_view, myFragment).commit()
        }

    }
}