package com.example.metro.Avtivity.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metro.Avtivity.Activities.MainActivity


@Database(entities = [Metro::class], version = 2)
abstract class MetroDatabase: RoomDatabase() {
        abstract fun metroDao():MetroDao

    companion object {
        @Volatile
        private var INSTANCE: MetroDatabase? = null

        fun getDatabase(context: Context): MetroDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    // Create database here
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MetroDatabase::class.java,
                        "metroData_db"
                    )
                        .allowMainThreadQueries() //allows Room to executing task in main thread
                        .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                        .build()

                    INSTANCE = instance
                    instance
                }
        }

        /*companion object{
            fun getDatabase(context: Context):MetroDatabase{
                val db = Room.databaseBuilder(
                    context,
                    MetroDatabase::class.java, "MetroDatabase.db"
                ).createFromAsset("database/MetroDatabase.db")
                    .allowMainThreadQueries()
                    .build()
                return db
            }
        }*/

    }
}