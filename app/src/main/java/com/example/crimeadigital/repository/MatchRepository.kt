package com.example.crimeadigital.repository

import android.content.Context
import com.example.crimeadigital.model.MatchDetail
import com.example.crimeadigital.model.MatchEntity
import com.example.crimeadigital.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MatchRepository(context: Context) {
    private val matchDao = MatchDatabase.getDatabase(context).matchDao()

    suspend fun getMatches(): List<MatchDetail> {
        return withContext(Dispatchers.IO) {
            val cachedMatches = matchDao.getAllMatches().map {
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

            cachedMatches.ifEmpty {
                val response = RetrofitClient.apiService.getMatches()
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
    }

    suspend fun findMatchesByTeamName(teamName: String): List<MatchDetail> {
        return withContext(Dispatchers.IO) {
            matchDao.findMatchesByTeamName("%$teamName%").map {
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
}