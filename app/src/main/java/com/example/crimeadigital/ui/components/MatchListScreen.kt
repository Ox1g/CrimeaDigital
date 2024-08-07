package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.crimeadigital.data.local.MatchEntity
import com.example.crimeadigital.domain.model.Match
import com.example.crimeadigital.presentation.MatchViewModel

@Composable
fun MatchListScreen(
    navController: NavController,
    matchViewModel: MatchViewModel,
    onSwitchLayoutClick: () -> Unit,
    isListView: Boolean,
    pagedMatches: LazyPagingItems<MatchEntity>
) {
    val searchText by matchViewModel.searchText.observeAsState("")
    val searchResults by matchViewModel.searchResults.observeAsState(emptyList())

    var isSearchBarVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppBarCompose(
                title = "Match List",
                onSwitchLayoutClick = onSwitchLayoutClick,
                switchIcon = if (isListView) Icons.AutoMirrored.Filled.List else Icons.Default.Close,
                contentDescription = "Switch Layout",
                showSearchBar = isSearchBarVisible,
                searchText = searchText,
                onSearchTextChange = { newText -> matchViewModel.onSearchTextChange(newText) },
                onSearchClick = { matchViewModel.searchMatches(searchText) },
                onToggleSearchBar = {
                    isSearchBarVisible = !isSearchBarVisible
                    if (!isSearchBarVisible) {
                        matchViewModel.onSearchTextChange("")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (searchResults.isNotEmpty()) {
                if (isListView) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(searchResults) { match ->
                            MatchListItem(match = match.toEntity(), onClick = {
                                navController.navigate("match_detail/${match.matchNumber}")
                            })
                            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(searchResults) { match ->
                            MatchListItem(match = match.toEntity(), onClick = {
                                navController.navigate("match_detail/${match.matchNumber}")
                            })
                            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                        }
                    }
                }
            } else {
                when (pagedMatches.loadState.refresh) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    else -> {
                        if (isListView) {
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(pagedMatches.itemCount) { index ->
                                    val match = pagedMatches[index]
                                    match?.let {
                                        MatchListItem(match = it, onClick = {
                                            navController.navigate("match_detail/${it.matchNumber}")
                                        })
                                        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                                    }
                                }
                            }
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(pagedMatches.itemCount) { index ->
                                    val match = pagedMatches[index]
                                    match?.let {
                                        MatchListItem(match = it, onClick = {
                                            navController.navigate("match_detail/${it.matchNumber}")
                                        })
                                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Match.toEntity(): MatchEntity {
    return MatchEntity(
        matchNumber = this.matchNumber,
        roundNumber = this.roundNumber,
        dateUtc = this.dateUtc,
        location = this.location,
        homeTeam = this.homeTeam,
        awayTeam = this.awayTeam,
        group = this.group,
        homeTeamScore = this.homeTeamScore,
        awayTeamScore = this.awayTeamScore
    )
}
