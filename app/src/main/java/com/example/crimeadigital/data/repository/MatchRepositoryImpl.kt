package com.example.crimeadigital.data.repository

import android.util.Log
import com.example.crimeadigital.data.local.MatchDao
import com.example.crimeadigital.data.local.MatchEntity
import com.example.crimeadigital.data.remote.ApiService
import com.example.crimeadigital.domain.model.Match
import com.example.crimeadigital.domain.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MatchRepositoryImpl(
    private val matchDao: MatchDao,
    private val apiService: ApiService
) : MatchRepository {

    override fun getMatches(): Flow<List<Match>> {
        return matchDao.getAllMatches().map { matchEntities ->
            matchEntities.map {
                Match(
                    matchNumber = it.MatchNumber,
                    roundNumber = it.RoundNumber,
                    dateUtc = it.DateUtc,
                    location = it.Location,
                    homeTeam = it.HomeTeam,
                    awayTeam = it.AwayTeam,
                    group = it.Group.toString(),
                    homeTeamScore = it.HomeTeamScore ?: 0,
                    awayTeamScore = it.AwayTeamScore ?: 0
                )
            }
        }
    }

    override suspend fun fetchAndCacheMatches() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMatches()
                Log.d("MatchRepository", "API response: $response")
                val matchEntities = response.map {
                    MatchEntity(
                        MatchNumber = it.MatchNumber,
                        RoundNumber = it.RoundNumber,
                        DateUtc = it.DateUtc,
                        Location = it.Location,
                        HomeTeam = it.HomeTeam,
                        AwayTeam = it.AwayTeam,
                        Group = it.Group,
                        HomeTeamScore = it.HomeTeamScore,
                        AwayTeamScore = it.AwayTeamScore
                    )
                }
                matchDao.insertAll(matchEntities)
                Log.d("MatchRepository", "Data inserted into database")
            } catch (e: Exception) {
                Log.e("MatchRepository", "Error fetching matches", e)
            }
        }
    }

}
