package ru.kykapek.starwarswiki.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.kykapek.starwarswiki.api.ApiService
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.utils.Constants.FIRST_PAGE_INDEX
/*
class FilmsPagingSource(private val apiService: ApiService, private val search: String): PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {

        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = apiService.getFilms(position)
            val films = response.results
            val filteredData = films.filter { it.title.contains(search, true) }
            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1
            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

}*/