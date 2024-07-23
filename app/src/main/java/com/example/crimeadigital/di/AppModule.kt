package com.example.crimeadigital.di

import android.content.Context
import androidx.room.Room
import com.example.crimeadigital.network.ApiService
import com.example.crimeadigital.network.RetrofitClient
import com.example.crimeadigital.repository.MatchDao
import com.example.crimeadigital.repository.MatchDatabase
import com.example.crimeadigital.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MatchDatabase {
        return Room.databaseBuilder(context, MatchDatabase::class.java, "match_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMatchDao(database: MatchDatabase): MatchDao {
        return database.matchDao()
    }

    @Provides
    @Singleton
    fun provideMatchRepository(matchDao: MatchDao, apiService: ApiService): MatchRepository {
        return MatchRepository(matchDao, apiService)
    }
}
