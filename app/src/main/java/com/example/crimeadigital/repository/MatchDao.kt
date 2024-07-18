package com.example.crimeadigital.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crimeadigital.model.MatchEntity

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matches: List<MatchEntity>)

    @Query("SELECT * FROM matches")
    suspend fun getAllMatches(): List<MatchEntity>

    @Query("SELECT * FROM matches WHERE HomeTeam LIKE :teamName OR AwayTeam LIKE :teamName")
    suspend fun findMatchesByTeamName(teamName: String): List<MatchEntity>
}