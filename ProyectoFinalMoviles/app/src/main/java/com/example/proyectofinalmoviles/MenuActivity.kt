package com.example.proyectofinalmoviles

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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
            myNavigationView.setNavigationItemSelectedListener {
                if (it.itemId == R.id.menu_home){
                    val myFragmentManager: FragmentManager = supportFragmentManager
                    val myFragmentTransaction: FragmentTransaction = myFragmentManager.beginTransaction()
                    val myFragment: WebFragment = WebFragment.newInstance("http://localhost:8000/")
                    myFragmentTransaction.replace(R.id.myLinearL, myFragment).commit()
                }
                true
            }
            onBackPressedDispatcher.addCallback {
                if (main.isDrawerOpen(GravityCompat.START)){
                    main.closeDrawer(GravityCompat.START)
                }
                else{
                    finish()
                }
            }

        }

    }
}