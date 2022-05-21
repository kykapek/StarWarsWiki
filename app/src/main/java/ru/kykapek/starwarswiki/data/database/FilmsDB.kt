package ru.kykapek.starwarswiki.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kykapek.starwarswiki.models.Film

@Database(
    entities = [Film::class],
    version = 1
)
abstract class FilmsDB : RoomDatabase() {

    abstract fun filmsDao() : FilmsDao

    companion object {

        private var INSTANCE: FilmsDB? = null

        fun getDatabase(context: Context) : FilmsDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    FilmsDB::class.java,
                    "filmsDB"
                ).build()
            }
            return INSTANCE!!
        }
    }

}