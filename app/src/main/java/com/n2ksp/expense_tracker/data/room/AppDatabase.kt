package com.n2ksp.expense_tracker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CategoryDBModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): CategoryDao

    companion object {
        private const val DATA_BASE_NAME = "my_money_db"
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {

                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATA_BASE_NAME)
                        .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}