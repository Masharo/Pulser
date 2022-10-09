package com.masharo.pulser.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.masharo.pulser.data.model.Coords

@Database(entities = [Coords::class], version = 1, exportSchema = false)
abstract class PulserDatabase: RoomDatabase() {

    abstract fun getPulserDao(): PulserDao

    companion object {
        private const val NAME_DB = "pulser_db"
        @Volatile
        private var db_local: PulserDatabase? = null
        private val db: PulserDatabase by lazy(LazyThreadSafetyMode.NONE) {
            db_local ?: throw Exception("Ошибка инициализации БД")
        }

        fun instance(context: Context): PulserDatabase {
            db_local ?: run {
                @Synchronized
                if (db_local == null) {
                    db_local = Room
                        .databaseBuilder(
                            context,
                            PulserDatabase::class.java,
                            NAME_DB
                        ).build()
                }
            }

            return db
        }
    }

}