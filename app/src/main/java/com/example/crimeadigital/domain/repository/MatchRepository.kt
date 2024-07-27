package com.example.crimeadigital.domain.repository

import com.example.crimeadigital.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatches(): Flow<List<Match>>
    suspend fun fetchAndCacheMatches()
}
