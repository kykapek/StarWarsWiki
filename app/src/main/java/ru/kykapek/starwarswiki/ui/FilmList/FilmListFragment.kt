package ru.kykapek.starwarswiki.ui.FilmList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ru.kykapek.starwarswiki.databinding.FragmentFilmListBinding
import kotlin.reflect.KProperty

@AndroidEntryPoint
class FilmListFragment : Fragment() {

    private lateinit var mBinding: FragmentFilmListBinding
    private var viewModel: FilmListViewModel by viewModels()

    private val filmsAdapter: FilmListAdapter by lazy {
        FilmListAdapter(FilmListAdapter.OnClickListener { film ->
            val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(
                //film
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
        mBinding = FragmentFilmListBinding.inflate(inflater, container, false)
        val view = mBinding.root
        mBinding.ilSearch.setEndIconOnClickListener {
            setUpObserver(mBinding.ilSearch.editText!!.text.toString())
            mBinding.progressBarFilms.isVisible = true
        }
        setUpObserver("")
        setUpAdapter()
        return view
    }

    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getFilms(searchString).collect {
                filmsAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setUpAdapter() = with(mBinding) {
        rvFilms.adapter = filmsAdapter

        filmsAdapter.addLoadStateListener { combinedLoadStates ->
            if (combinedLoadStates.refresh is LoadState.Loading) {
                if (filmsAdapter.snapshot().isEmpty()) {
                    progressBarFilms.isVisible = true
                }
                tvError.isVisible = false
            } else {
                progressBarFilms.isVisible = false

                val error = when {
                    combinedLoadStates.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                    combinedLoadStates.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                    combinedLoadStates.refresh is LoadState.Error -> combinedLoadStates.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    if (filmsAdapter.snapshot().isEmpty()) {
                        tvError.isVisible = true
                        tvError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }
}

private operator fun Any.setValue(filmListFragment: FilmListFragment, property: KProperty<*>, filmListViewModel: FilmListViewModel) {

}
