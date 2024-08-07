    package com.example.crimeadigital

    import android.os.Bundle
    import androidx.activity.compose.setContent
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import com.example.crimeadigital.presentation.MatchViewModel
    import com.example.crimeadigital.ui.navigation.MainScreen
    import com.example.crimeadigital.ui.theme.CrimeaDigitalTheme
    import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint
    class MainActivity : AppCompatActivity() {


        private val matchViewModel: MatchViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContent {
                CrimeaDigitalTheme {
                    MainScreen(viewModel = matchViewModel)
                }
            }
        }
    }
