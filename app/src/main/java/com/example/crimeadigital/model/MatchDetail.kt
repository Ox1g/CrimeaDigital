package com.example.crimeadigital.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class MatchDetail(
    val MatchNumber: Int,
    val RoundNumber: Int,
    val DateUtc: String,
    val Location: String,
    val HomeTeam: String,
    val AwayTeam: String,
    val Group: String?,
    val HomeTeamScore: Int,
    val AwayTeamScore: Int
):Parcelable