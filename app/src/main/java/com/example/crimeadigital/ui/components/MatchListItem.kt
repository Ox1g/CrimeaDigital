package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crimeadigital.model.MatchDetail

@Composable
fun MatchListItem(match: MatchDetail, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Text(text = "Match Number: ${match.MatchNumber}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Home Team: ${match.HomeTeam} vs Away Team: ${match.AwayTeam}", style = MaterialTheme.typography.bodySmall)
    }
}
