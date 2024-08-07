package com.example.crimeadigital.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.crimeadigital.ui.components.MatchDetailScreen
import com.example.crimeadigital.ui.components.MatchListScreen
import com.example.crimeadigital.presentation.MatchViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(viewModel: MatchViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var isListView by rememberSaveable { mutableStateOf(true) }
    val pagedMatches = viewModel.pagedMatches.collectAsLazyPagingItems()

    NavHost(navController = navController, startDestination = "match_list") {
        composable("match_list") {
            MatchListScreen(
                navController = navController,
                matchViewModel = viewModel,
                isListView = isListView,
                onSwitchLayoutClick = { isListView = !isListView },
                pagedMatches = pagedMatches
            )
        }
        composable(
            "match_detail/{matchNumber}",
            arguments = listOf(navArgument("matchNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val matchNumber = backStackEntry.arguments?.getInt("matchNumber")
            val matchDetail = viewModel.matches.value.find { it.matchNumber == matchNumber }
            matchDetail?.let {
                MatchDetailScreen(navController = navController, matchResponse = it)
            } ?: run {
                LoadingIndicator()
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
