package com.example.crimeadigital.repository

import com.example.crimeadigital.model.MatchDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import com.example.crimeadigital.utils.Result

class MatchRepository {

    fun getMatches(): Flow<Result<List<MatchDetail>>> = flow {
        try {
            emit(Result.Loading)
            delay(2000) // Симуляция задержки сети
            val matches = generateFakeValues()
            emit(Result.Success(matches))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    private fun generateFakeValues(): List<MatchDetail> {
        return List(20) { i ->
            MatchDetail(
                MatchNumber = i,
                RoundNumber = 1,
                DateUtc = "2023-07-08",
                Location = "Stadium $i",
                HomeTeam = "Team $i",
                AwayTeam = "Team ${i + 1}",
                Group = "Group A",
                HomeTeamScore = i,
                AwayTeamScore = i + 1
            )
        }
    }
}
