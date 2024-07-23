package com.example.crimeadigital.repository

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crimeadigital.model.MatchEntity

@Database(entities = [MatchEntity::class], version = 1, exportSchema = false)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao

//    companion object {
//        @Volatile
//        private var INSTANCE: MatchDatabase? = null
//
//        fun getDatabase(context: MatchDao): MatchDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MatchDatabase::class.java,
//                    "match_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}