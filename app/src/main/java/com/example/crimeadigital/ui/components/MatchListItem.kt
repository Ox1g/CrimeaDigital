    package com.example.crimeadigital.ui.components

    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.*
    import androidx.compose.material3.*
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp
    import com.example.crimeadigital.data.local.MatchEntity

    @Composable
    fun MatchListItem(match: MatchEntity, onClick: () -> Unit) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = onClick)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Match Number: ${match.matchNumber}", style = MaterialTheme.typography.bodyLarge , color = MaterialTheme.colorScheme.primary)
            Text(text = "Home Team: ${match.homeTeam} vs Away Team: ${match.awayTeam}", style = MaterialTheme.typography.bodySmall , color = MaterialTheme.colorScheme.secondary)
            Text(text = "Date: ${match.dateUtc}", style = MaterialTheme.typography.bodySmall , color = MaterialTheme.colorScheme.secondary)
        }
    }
