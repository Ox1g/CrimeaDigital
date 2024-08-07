package com.example.crimeadigital.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
                    matchNumber = it.matchNumber,
                    roundNumber = it.roundNumber,
                    dateUtc = it.dateUtc,
                    location = it.location,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    group = it.group.toString(),
                    homeTeamScore = it.homeTeamScore,
                    awayTeamScore = it.awayTeamScore
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
                        matchNumber = it.MatchNumber,
                        roundNumber = it.RoundNumber,
                        dateUtc = it.DateUtc,
                        location = it.Location,
                        homeTeam = it.HomeTeam,
                        awayTeam = it.AwayTeam,
                        group = it.Group,
                        homeTeamScore = it.HomeTeamScore,
                        awayTeamScore = it.AwayTeamScore
                    )
                }
                matchDao.insertAll(matchEntities)
                Log.d("MatchRepository", "Data inserted into database")
            } catch (e: Exception) {
                Log.e("MatchRepository", "Error fetching matches", e)
            }
        }
    }

    override fun getMatchesPaged(): Flow<PagingData<MatchEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { matchDao.getMatchesPaged() }
        ).flow
    }

    override fun searchMatchesByTeamName(teamName: String): Flow<List<Match>> {
        return matchDao.searchMatchesByTeamName(teamName).map { matchEntities ->
            matchEntities.map { it.toDomainModel() }
        }
    }

    private fun MatchEntity.toDomainModel(): Match {
        return Match(
            matchNumber = matchNumber,
            roundNumber = roundNumber,
            dateUtc = dateUtc,
            location = location,
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            group = group,
            homeTeamScore = homeTeamScore,
            awayTeamScore = awayTeamScore
        )
    }
}

