package com.v2px.sujin.roomrelationtest

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.webkit.CookieSyncManager.createInstance
import com.v2px.sujin.roomrelationtest.dao.UserRepoDao
import com.v2px.sujin.roomrelationtest.model.Repo
import com.v2px.sujin.roomrelationtest.model.User

@Database(entities = arrayOf(User::class, Repo::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME: String = "appDatabase.db"

        var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance as AppDatabase
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getUserRepoDAO(): UserRepoDao

}