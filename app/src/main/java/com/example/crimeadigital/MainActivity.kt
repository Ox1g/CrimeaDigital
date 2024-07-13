package com.example.crimeadigital

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.crimeadigital.databinding.ActivityMainBinding
import com.example.crimeadigital.fragments.MatchListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.customToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Custom App Bar"

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.actionSwitchLayout.setOnClickListener {
            val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull() as? MatchListFragment
            currentFragment?.switchLayout()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        return navController.navController.navigateUp() || super.onSupportNavigateUp()
    }
}
