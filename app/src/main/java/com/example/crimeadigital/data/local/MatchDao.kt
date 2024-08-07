package com.example.crimeadigital.data.local

import androidx.paging.PagingSource
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

    @Query("SELECT * FROM matches WHERE homeTeam LIKE '%' || :teamName || '%' OR awayTeam LIKE '%' || :teamName || '%'")
    fun searchMatchesByTeamName(teamName: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches ORDER BY matchNumber ASC")
    fun getMatchesPaged(): PagingSource<Int, MatchEntity>
}
