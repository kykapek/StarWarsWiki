package ru.kykapek.starwarswiki.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.Hero
import ru.kykapek.starwarswiki.models.converters.ListConverter

@Database(
    entities = [Film::class, Hero::class],
    version = 7
)
@TypeConverters(ListConverter::class)
abstract class FilmsDB : RoomDatabase() {

    abstract fun filmsDao() : FilmsDao

    companion object {

        @Volatile private var INSTANCE: FilmsDB? = null

        fun getDatabase(context: Context) : FilmsDB =
            INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, FilmsDB::class.java, "films")
                .fallbackToDestructiveMigration()
                .build()
    }

}