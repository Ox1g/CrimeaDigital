package com.example.crimeadigital.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.crimeadigital.domain.model.Match

@Composable
fun MatchListItem(match: Match, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Text(text = "Match Number: ${match.matchNumber}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Home Team: ${match.homeTeam} vs Away Team: ${match.awayTeam}", style = MaterialTheme.typography.bodySmall)
    }
}
