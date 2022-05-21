package ru.kykapek.starwarswiki.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ru.kykapek.starwarswiki.api.ApiCall
import ru.kykapek.starwarswiki.api.ApiService
import ru.kykapek.starwarswiki.models.Film
import kotlinx.coroutines.flow.Flow
import ru.kykapek.starwarswiki.utils.Constants.PAGE_SIZE
import javax.inject.Inject

class FilmsRepository @Inject constructor(private val apiService: ApiService) : ApiCall() {

    /*
    fun getFilms(search: String): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FilmsPagingSource(apiService, search)
            }
        ).flow
    }

     */

    fun saveAllFilms() {
        
    }

    fun getFilmsDB() {

    }

    /*
    suspend fun getHero(url: String) = safeApiCall {
        apiService.getHero(url)
    }

     */

}