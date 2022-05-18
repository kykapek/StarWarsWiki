package ru.kykapek.starwarswiki.ui.FilmList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.kykapek.starwarswiki.R
import ru.kykapek.starwarswiki.databinding.FragmentFilmListBinding

class FilmListFragment : Fragment() {

    private lateinit var mBinding: FragmentFilmListBinding
    private var viewModel: FilmListViewModel by viewModels()

    private val filmsAdapter: FilmListAdapter by lazy {
        FilmListAdapter(FilmListAdapter.onClickListener { film ->
            val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(
                film
            )
            findNavController().navigate(action)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_list, container, false)
    }

}