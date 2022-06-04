package ru.kykapek.starwarswiki.data.remote

import retrofit2.Response
import retrofit2.http.*
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.FilmList
import ru.kykapek.starwarswiki.models.Hero
import ru.kykapek.starwarswiki.models.response.FilmResponse

interface FilmsService {

    @GET("films/")
    suspend fun getFilms() : Response<FilmList>

    @GET("films/{id}")
    suspend fun getFilm(@Path("id") id: Int): Response<Film>

    @GET("people/{id}")
    suspend fun getHero(@Path("id") id: Int): Response<Hero>

    @GET("films/{id}")
    suspend fun getHeroesFilm(@Path("id") id: Int): Response<FilmResponse>

}