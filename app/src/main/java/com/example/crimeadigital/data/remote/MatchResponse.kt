package com.example.crimeadigital.data.remote

data class MatchResponse(
    val MatchNumber: Int,
    val RoundNumber: Int,
    val DateUtc: String,
    val Location: String,
    val HomeTeam: String,
    val AwayTeam: String,
    val Group: String?,
    val HomeTeamScore: Int,
    val AwayTeamScore: Int
)