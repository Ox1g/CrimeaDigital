package com.example.crimeadigital.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MatchEntity::class], version = 4, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}