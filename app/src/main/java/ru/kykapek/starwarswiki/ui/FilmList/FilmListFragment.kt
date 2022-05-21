package ru.kykapek.starwarswiki.ui.FilmList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ru.kykapek.starwarswiki.R
import ru.kykapek.starwarswiki.databinding.FragmentFilmListBinding
import ru.kykapek.starwarswiki.models.Film
import ru.kykapek.starwarswiki.utils.Resource
import timber.log.Timber
import kotlin.reflect.KProperty

@AndroidEntryPoint
class FilmListFragment : Fragment(), FilmListNewAdapter.FilmItemListener {

    private lateinit var mBinding: FragmentFilmListBinding
    private var viewModel: FilmListViewModel by viewModels()

    private lateinit var adapter: FilmListNewAdapter

    /*
    private val filmsAdapter: FilmListAdapter by lazy {
        FilmListAdapter(FilmListAdapter.OnClickListener { film ->
            val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(film)
            findNavController().navigate(action)
        })
    }
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFilmListBinding.inflate(inflater, container, false)

        mBinding.ilSearch.setEndIconOnClickListener {
            if (mBinding.etSearch.text.toString().isNotEmpty()) {
                getItemsFromDb(mBinding.etSearch.text.toString())
            }
        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObserver()
    }

    private fun getItemsFromDb(search: String) {
        var searchText = search
        searchText = "%$search%"

        viewModel.searchForFilms(searchText).observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                Timber.e(list.toString())
                adapter.setItems(list as ArrayList<Film>)
            }
        })
    }

    private fun setUpRecyclerView() {
        adapter = FilmListNewAdapter(this)
        mBinding.rvFilms.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvFilms.adapter = adapter

    }

    private fun setUpObserver() {
        viewModel.getFilmsDBRepository.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    mBinding.progressBarFilms.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        adapter.setItems(ArrayList(it.data))
                    }
                }
                Resource.Status.LOADING -> mBinding.progressBarFilms.visibility = View.VISIBLE
                Resource.Status.ERROR -> {
                    mBinding.progressBarFilms.visibility = View.GONE
                    mBinding.tvError.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClickedFilm(film: Film) {
        val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(film)
        findNavController().navigate(
            action
        )
    }
}

private operator fun Any.setValue(filmListFragment: FilmListFragment, property: KProperty<*>, filmListViewModel: FilmListViewModel) {

}

/*
@AndroidEntryPoint
class FilmListFragment : Fragment() {

    private lateinit var mBinding: FragmentFilmListBinding
    private var viewModel: FilmListViewModel by viewModels()

    private val filmsAdapter: FilmListAdapter by lazy {
        FilmListAdapter(FilmListAdapter.OnClickListener { film ->
            val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(film)
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
 */
