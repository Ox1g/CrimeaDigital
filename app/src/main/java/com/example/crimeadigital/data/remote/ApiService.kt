package com.example.crimeadigital.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET(ENDPOINT)
    suspend fun getMatches(): List<MatchResponse>

    companion object {
        private const val BASE_URL = "https://fixturedownload.com/"
        private const val ENDPOINT = "feed/json/epl-2021"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
