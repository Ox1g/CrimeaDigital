package com.example.crimeadigital.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crimeadigital.ui.components.MatchDetailScreen
import com.example.crimeadigital.ui.components.MatchListScreen
import com.example.crimeadigital.presentation.MatchViewModel

@Composable
fun MainScreen(viewModel: MatchViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var isListView by rememberSaveable { mutableStateOf(true) }
    val matches by viewModel.matches.collectAsState()
    val loading by viewModel.loading.collectAsState()

    NavHost(navController = navController, startDestination = "match_list") {
        composable("match_list") {
            if (loading) {
                LoadingIndicator()
            } else {
                MatchListScreen(
                    navController = navController,
                    isListView = isListView,
                    onSwitchLayoutClick = { isListView = !isListView },
                    matches = matches
                )
            }
        }
        composable(
            "match_detail/{matchNumber}",
            arguments = listOf(navArgument("matchNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val matchNumber = backStackEntry.arguments?.getInt("matchNumber")
            val matchDetail = matches.find { it.matchNumber == matchNumber }
            if (matchDetail != null) {
                MatchDetailScreen(navController = navController, matchResponse = matchDetail)
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
