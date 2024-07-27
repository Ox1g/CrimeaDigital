package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crimeadigital.domain.model.Match

@Composable
fun MatchDetailScreen(
    navController: NavController,
    matchResponse: Match?
) {
    Scaffold(
        topBar = {
            AppBarCompose(
                title = "Match Details",
                onSwitchLayoutClick = {},
                switchIcon = Icons.AutoMirrored.Filled.List,
                contentDescription = "Switch Layout",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = "Match Number: ${matchResponse?.matchNumber}")
            Text(text = "Round Number: ${matchResponse?.roundNumber}")
            Text(text = "Date: ${matchResponse?.dateUtc}")
            Text(text = "Location: ${matchResponse?.location}")
            Text(text = "Home Team: ${matchResponse?.homeTeam}")
            Text(text = "Away Team: ${matchResponse?.awayTeam}")
            Text(text = "Group Team: ${matchResponse?.group}")
            Text(text = "Home Team Score: ${matchResponse?.homeTeamScore}")
            Text(text = "Away Team Score: ${matchResponse?.awayTeamScore}")
        }
    }
}
