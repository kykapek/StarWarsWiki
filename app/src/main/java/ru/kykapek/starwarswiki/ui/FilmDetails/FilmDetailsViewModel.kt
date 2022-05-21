package ru.kykapek.starwarswiki.ui.FilmDetails

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kykapek.starwarswiki.data.FilmsRepository
import ru.kykapek.starwarswiki.data.database.FilmsDBRepository
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.Hero
import ru.kykapek.starwarswiki.models.response.FilmResponse
import ru.kykapek.starwarswiki.utils.Resource
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    //private val filmsRepository: FilmsRepository,
    private val filmsDBRepository: FilmsDBRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val heroesfromfilms = filmsDBRepository.hetHeroes(1)

    fun getHeroes(id: Int) : LiveData<Resource<FilmResponse>> {

        Log.e("List of heroes links", filmsDBRepository.hetHeroes(id).toString())
        return filmsDBRepository.hetHeroes(id)
    }

    /*

    private val arguments = savedStateHandle.get<Film>("filmDetails")

    private val _details = MutableLiveData<Film>()
    val details : LiveData<Film>
        get() = _details

    private val _heroDetails = MutableStateFlow<Resource<List<Hero>>>(Resource.Empty())
    val heroDetails: StateFlow<Resource<List<Hero>>>
        get() = _heroDetails

    private val heroList: ArrayList<Hero> = ArrayList()

    init {
        _details.value = arguments!!
        getHeroesData()
    }

    private fun getHeroesData() {
        arguments!!.characters.forEach { hero ->
            viewModelScope.launch(Dispatchers.IO) {
                _heroDetails.value = Resource.Loading()
                when (val filmDetainsResponse = filmsRepository.getHero(hero)) {
                    is Resource.Failure -> {
                        _heroDetails.value = Resource.Failure(filmDetainsResponse.message!!)
                    }
                    is Resource.Success -> {
                        if(filmDetainsResponse.data == null) {
                            _heroDetails.value = Resource.Failure("Empty Hero List")
                        } else {
                            heroList.add(filmDetainsResponse.data)
                            _heroDetails.value = Resource.Success(heroList)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

     */

}