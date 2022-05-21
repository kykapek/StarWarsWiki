package ru.kykapek.starwarswiki.data.remote

import javax.inject.Inject

class FilmsRemoteDataSource @Inject constructor(
    private val filmsService: FilmsService
) : BaseDataSource() {

    suspend fun getFilms() = getResult { filmsService.getFilms() }

    suspend fun getFilm(id: Int) = getResult { filmsService.getFilm(id) }

    suspend fun getHeroes(id: Int) = getResult { filmsService.getHeroesFilm(id) }

}