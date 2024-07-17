package com.example.crimeadigital.network

import com.example.crimeadigital.model.MatchDetail
import retrofit2.http.GET

interface ApiService {
    @GET("feed/json/epl-2021")
    suspend fun getMatches(): List<MatchDetail>
}
