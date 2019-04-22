package com.n2ksp.expense_tracker.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.n2ksp.expense_tracker.utils.SqlLiteUtil


@Database(entities = [CategoryDBModel::class, IncomeExpenseDBModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun incomeExpenseDao(): IncomeExpenseDao

    companion object {
        private const val DATA_BASE_NAME = "my_money_db"
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {

                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATA_BASE_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(SqlLiteUtil.createExpenseTable)
    }
}