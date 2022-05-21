package ru.kykapek.starwarswiki.data.database

import androidx.lifecycle.LiveData
import ru.kykapek.starwarswiki.data.remote.FilmsRemoteDataSource
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.utils.performGetOperation
import ru.kykapek.starwarswiki.utils.performGetSingleOperation
import javax.inject.Inject

class FilmsDBRepository @Inject constructor(
    private val remoteDataSource: FilmsRemoteDataSource,
    private val filmsDao: FilmsDao
) {

    fun getFilm(id: Int) = performGetOperation(
        databaseQuery = { filmsDao.getFilm(id) },
        networkCall = { remoteDataSource.getFilm(id) },
        saveCallResult = { filmsDao.insertFilm(it) }
    )

    fun getFilms() = performGetOperation(
        databaseQuery = { filmsDao.getFilms() },
        networkCall = { remoteDataSource.getFilms() },
        saveCallResult = { filmsDao.insertAllFilms(it.results) }
    )

    fun searchFilms(search: String) : LiveData<List<Film>> {
        return filmsDao.getSearchFilmsResult(search)
    }

    suspend fun getHeroes(id: Int) = remoteDataSource.getHeroes(id)

    fun hetHeroes(id: Int) = performGetSingleOperation( networkCall = { remoteDataSource.getHeroes(id) } )


}