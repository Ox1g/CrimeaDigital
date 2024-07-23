package com.example.crimeadigital

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.crimeadigital.repository.MatchRepository
//import com.example.crimeadigital.fragments.MatchListFragment
import com.example.crimeadigital.ui.navigation.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var matchRepository: MatchRepository
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    MainScreen(matchRepository)
                }
            }
        }

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val toolbar: Toolbar = binding.customToolbar
//        setSupportActionBar(toolbar)
//        supportActionBar?.title = "Custom App Bar"
//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.actionSwitchLayout.setOnClickListener {
//            val currentFragment = navHostFragment.childFragmentManager.fragments.firstOrNull() as? MatchListFragment
//            currentFragment?.switchLayout()
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        return navController.navController.navigateUp() || super.onSupportNavigateUp()
//    }
}
