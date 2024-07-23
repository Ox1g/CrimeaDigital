package com.example.crimeadigital.repository

import com.example.crimeadigital.model.MatchDetail
import com.example.crimeadigital.model.MatchEntity
import com.example.crimeadigital.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

open class MatchRepository(private val matchDao: MatchDao, private val apiService: ApiService) {

    fun getMatches(): Flow<List<MatchDetail>> {
        return matchDao.getAllMatches().map { matchEntities ->
            matchEntities.map {
                MatchDetail(
                    MatchNumber = it.MatchNumber,
                    RoundNumber = it.RoundNumber,
                    DateUtc = it.DateUtc,
                    Location = it.Location,
                    HomeTeam = it.HomeTeam,
                    AwayTeam = it.AwayTeam,
                    Group = it.Group,
                    HomeTeamScore = it.HomeTeamScore ?: 0,
                    AwayTeamScore = it.AwayTeamScore ?: 0
                )
            }
        }
    }

    fun findMatchesByTeamName(teamName: String): Flow<List<MatchDetail>> {
        return matchDao.findMatchesByTeamName("%$teamName%").map { matchEntities ->
            matchEntities.map {
                MatchDetail(
                    MatchNumber = it.MatchNumber,
                    RoundNumber = it.RoundNumber,
                    DateUtc = it.DateUtc,
                    Location = it.Location,
                    HomeTeam = it.HomeTeam,
                    AwayTeam = it.AwayTeam,
                    Group = it.Group,
                    HomeTeamScore = it.HomeTeamScore ?: 0,
                    AwayTeamScore = it.AwayTeamScore ?: 0
                )
            }
        }
    }

    suspend fun fetchAndCacheMatches() {
        withContext(Dispatchers.IO) {
            val response = apiService.getMatches()
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
        }
    }
}
