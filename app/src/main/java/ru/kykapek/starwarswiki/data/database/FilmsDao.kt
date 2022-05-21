package ru.kykapek.starwarswiki.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kykapek.starwarswiki.models.Film

@Dao
interface FilmsDao {

    @Insert
    suspend fun insertFilm(film: Film)

    @Query("SELECT * FROM films")
    suspend fun getFilms() : List<Film>

}