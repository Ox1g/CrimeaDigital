package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crimeadigital.model.MatchDetail

@Composable
fun MatchListScreen(
    navController: NavController,
    onSwitchLayoutClick: () -> Unit,
    isListView: Boolean,
    matches: List<MatchDetail>

) {
    Scaffold(
        topBar = {
            AppBarCompose(
                title = "Match List",
                onSwitchLayoutClick = onSwitchLayoutClick,
                switchIcon = if (isListView) Icons.Default.List else Icons.Default.Close,
                contentDescription = "Switch Layout"
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            if (matches.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (isListView) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(matches) { match ->
                            MatchListItem(match = match, onClick = {
                                navController.navigate("match_detail/${match.MatchNumber}")
                            })
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(matches) { match ->
                            MatchListItem(match = match, onClick = {
                                navController.navigate("match_detail/${match.MatchNumber}")
                            })
                        }
                    }
                }
            }
        }
    }
}
