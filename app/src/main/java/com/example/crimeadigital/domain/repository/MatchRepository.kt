package com.example.crimeadigital.domain.repository

import androidx.paging.PagingData
import com.example.crimeadigital.data.local.MatchEntity
import com.example.crimeadigital.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatches(): Flow<List<Match>>
    fun getMatchesPaged(): Flow<PagingData<MatchEntity>>
    suspend fun fetchAndCacheMatches()
    fun searchMatchesByTeamName(teamName: String): Flow<List<Match>>
}
