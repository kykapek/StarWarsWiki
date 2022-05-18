package ru.kykapek.starwarswiki.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.response.FilmResponse

interface ApiService {

    @GET("films/?page/")
    suspend fun getFilms(@Query("page") page: Int): FilmResponse

}