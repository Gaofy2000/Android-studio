package com.example.menuhamburguesa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.menuhamburguesa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
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
        with(binding){
            setSupportActionBar(myToolbar)
            val toggle= ActionBarDrawerToggle(this@MainActivity, main, myToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
            main.addDrawerListener(toggle)
            toggle.syncState()

            myNavigationView.setNavigationItemSelectedListener {
                if (it.itemId == R.id.id_inicio){
                    val myFragmentManager: FragmentManager = supportFragmentManager
                    val myFragmentTransaction: FragmentTransaction = myFragmentManager.beginTransaction()
                    val myFragment: Web_Fragment = Web_Fragment.newInstance("https://iesclaradelrey.es/portal/index.php/es/")
                    myFragmentTransaction.replace(R.id.webView, myFragment).commit()
                }
                true
            }

        }
    }


}