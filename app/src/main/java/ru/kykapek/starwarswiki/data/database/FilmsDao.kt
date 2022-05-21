package ru.kykapek.starwarswiki.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.Hero

@Dao
interface FilmsDao {

    //Films

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilms(films: List<Film>)

    @Query("SELECT * FROM films")
    fun getFilms() : LiveData<List<Film>>

    @Query("SELECT * FROM films WHERE id = :id")
    fun getFilm(id: Int): LiveData<Film>

    @Query("SELECT * FROM films WHERE title like :search")
    fun getSearchFilmsResult(search: String) : LiveData<List<Film>>

    //Heroes


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: Hero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHeroes(heroes: List<Hero>)

    @Query("SELECT * FROM heroes")
    fun getHeroes() : LiveData<List<Hero>>

    @Query("SELECT * FROM heroes WHERE episode_id = :id AND name = :name")
    fun getHero(id: Int, name: String): LiveData<Hero>




}