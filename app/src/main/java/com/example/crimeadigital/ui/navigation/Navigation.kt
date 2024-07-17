package com.example.crimeadigital.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crimeadigital.ui.components.MatchDetailScreen
import com.example.crimeadigital.ui.components.MatchListScreen
import com.example.crimeadigital.model.MatchDetail

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var isListView by rememberSaveable { mutableStateOf(true) }

    NavHost(navController, startDestination = "match_list") {
        composable("match_list") {
            MatchListScreen(
                navController = navController,
                isListView = isListView,
                onSwitchLayoutClick = {isListView = !isListView}
            )
        }
        composable(
            "match_detail/{matchNumber}",
            arguments = listOf(navArgument("matchNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val matchNumber = backStackEntry.arguments?.getInt("matchNumber") ?: return@composable
            val matchDetail = MatchDetail(
                MatchNumber = matchNumber,
                RoundNumber = 1,
                DateUtc = "2024-07-17",
                Location = "Location 1",
                HomeTeam = "Home Team 1",
                AwayTeam = "Away Team 1",
                Group = "Group A",
                HomeTeamScore = 2,
                AwayTeamScore = 1
            )
            MatchDetailScreen(navController = navController, matchDetail = matchDetail)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
