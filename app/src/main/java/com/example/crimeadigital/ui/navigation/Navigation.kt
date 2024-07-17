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
import com.example.crimeadigital.network.RetrofitClient

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var isListView by rememberSaveable { mutableStateOf(true) }
    var matches by remember { mutableStateOf<List<MatchDetail>?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val response = RetrofitClient.apiService.getMatches()
        matches = response.map {
            MatchDetail(
                MatchNumber = it.MatchNumber,
                RoundNumber = it.RoundNumber,
                DateUtc = it.DateUtc,
                Location = it.Location,
                HomeTeam = it.HomeTeam,
                AwayTeam = it.AwayTeam,
                Group = it.Group,
                HomeTeamScore = it.HomeTeamScore,
                AwayTeamScore = it.AwayTeamScore
            )
        }
        loading = false
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
                    matches = matches ?: emptyList()
                )
            }
        }
        composable(
            "match_detail/{matchNumber}",
            arguments = listOf(navArgument("matchNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val matchNumber = backStackEntry.arguments?.getInt("matchNumber") ?: return@composable
            val matchDetail = matches?.find { it.MatchNumber == matchNumber }
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


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
