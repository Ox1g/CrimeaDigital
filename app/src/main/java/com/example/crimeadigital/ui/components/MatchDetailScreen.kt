package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.crimeadigital.model.MatchDetail

@Composable
fun MatchDetailScreen(
    navController: NavController,
    matchDetail: MatchDetail
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
            Text(text = "Match Number: ${matchDetail.MatchNumber}")
            Text(text = "Round Number: ${matchDetail.RoundNumber}")
            Text(text = "Date: ${matchDetail.DateUtc}")
            Text(text = "Location: ${matchDetail.Location}")
            Text(text = "Home Team: ${matchDetail.HomeTeam}")
            Text(text = "Away Team: ${matchDetail.AwayTeam}")
            Text(text = "Group Team: ${matchDetail.Group}")
            Text(text = "Home Team Score: ${matchDetail.HomeTeamScore}")
            Text(text = "Away Team Score: ${matchDetail.AwayTeamScore}")
        }
    }
}
