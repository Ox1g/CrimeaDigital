package com.example.crimeadigital.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crimeadigital.ui.components.MatchDetailScreen
import com.example.crimeadigital.ui.components.MatchListScreen
import com.example.crimeadigital.model.MatchDetail
import com.example.crimeadigital.repository.MatchRepository
import kotlinx.coroutines.launch

@Composable
fun MainScreen(matchRepository: MatchRepository) {
    val navController = rememberNavController()
    var isListView by rememberSaveable { mutableStateOf(true) }
    var matches by remember { mutableStateOf<List<MatchDetail>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        launch {
            matchRepository.fetchAndCacheMatches()
            matchRepository.getMatches().collect { matchList ->
                matches = matchList
                loading = false
            }
        }
    }

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
            val matchNumber = backStackEntry.arguments?.getInt("matchNumber") ?: return@composable
            val matchDetail = matches.find { it.MatchNumber == matchNumber }
            if (matchDetail != null) {
                MatchDetailScreen(navController = navController, matchDetail = matchDetail)
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





