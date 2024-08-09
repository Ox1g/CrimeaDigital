package com.example.crimeadigital.domain.model

data class Match(
    val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val group: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?
)
