package ru.kykapek.starwarswiki.ui.FilmList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.kykapek.starwarswiki.data.FilmsRepository
import ru.kykapek.starwarswiki.models.Film
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(private val filmsRepository: FilmsRepository) : ViewModel() {
    fun getFilms(search: String): Flow<PagingData<Film>> {
        return filmsRepository.getFilms(search).cachedIn(viewModelScope)
    }
}