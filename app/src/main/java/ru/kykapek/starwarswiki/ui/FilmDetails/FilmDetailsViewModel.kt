package ru.kykapek.starwarswiki.ui.FilmDetails

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kykapek.starwarswiki.data.FilmsRepository
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.models.Hero
import ru.kykapek.starwarswiki.utils.Resource
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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

}