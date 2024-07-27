package com.example.crimeadigital.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matches: List<MatchEntity>)

    @Query("SELECT * FROM matches")
    fun getAllMatches(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE HomeTeam LIKE :teamName OR AwayTeam LIKE :teamName")
    fun findMatchesByTeamName(teamName: String): Flow<List<MatchEntity>>
}
