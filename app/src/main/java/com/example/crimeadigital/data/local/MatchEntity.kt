package com.example.crimeadigital.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val group: String,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?
) {
    fun getLocalDateTime(): String {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            val date = utcFormat.parse(dateUtc)
            val localFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            localFormat.timeZone = TimeZone.getDefault()
            date?.let { localFormat.format(it) } ?: dateUtc
        } catch (e: Exception) {
            dateUtc
        }
    }
}
