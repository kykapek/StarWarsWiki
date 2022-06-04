package ru.kykapek.starwarswiki.ui.FilmList

import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kykapek.starwarswiki.data.FilmsRepository
import ru.kykapek.starwarswiki.data.database.FilmsDBRepository
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.response.FilmResponse

@HiltViewModel
class FilmListViewModel @Inject constructor(
        private val filmsDBRepository: FilmsDBRepository
    ) : ViewModel() {

    /*
    fun getFilms(search: String): Flow<PagingData<Film>> {
        return filmsRepository.getFilms(search).cachedIn(viewModelScope)
    }
     */

    fun searchForFilms(search: String) : LiveData<List<Film>> {
        return filmsDBRepository.searchFilms(search)
    }

    val getFilmsDBRepository = filmsDBRepository.getFilms()

    //fun getCharacters
}