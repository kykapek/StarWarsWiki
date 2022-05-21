package ru.kykapek.starwarswiki.ui.FilmDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import ru.kykapek.starwarswiki.R
import ru.kykapek.starwarswiki.databinding.FragmentFilmDetailsBinding
import ru.kykapek.starwarswiki.ui.FilmList.FilmListAdapter
import ru.kykapek.starwarswiki.ui.FilmList.FilmListFragment
import ru.kykapek.starwarswiki.ui.FilmList.FilmListFragmentDirections
import ru.kykapek.starwarswiki.ui.FilmList.FilmListViewModel
import ru.kykapek.starwarswiki.utils.Resource
import kotlin.reflect.KProperty

@AndroidEntryPoint
class FilmDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentFilmDetailsBinding
    private val viewModel: FilmDetailsViewModel by viewModels()
    private val filmDetailsAdapter: FilmDetailsAdapter by lazy {
        FilmDetailsAdapter()
    }

    /*
    private val filmDetailsAdapter: FilmDetailsAdapter by lazy {
        FilmDetailsAdapter(FilmDetailsAdapter.OnClickListener { film ->
            val action = FilmListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment(
                //film
            )
            findNavController().navigate(action)
        })
    }
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        val view = mBinding.root
        sett()
        //viewModel.bla(1)
        /*

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.heroDetails.collect { event ->
                when(event) {
                    is Resource.Success -> {
                        mBinding.heroProgressBar.isVisible = false
                        filmDetailsAdapter.submitList(event.data)
                        mBinding.rvHeroes.adapter = filmDetailsAdapter
                    }
                    is Resource.Failure -> {
                        mBinding.heroProgressBar.isVisible = false
                        mBinding.tvHeroError.isVisible = true
                        mBinding.tvHeroError.text = event.message
                    }
                    is Resource.Loading -> {
                        mBinding.heroProgressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }

         */
        return view
    }

    fun sett() {
        viewModel.heroesfromfilms.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    Log.e("List of my links", it.data.toString())
                }
                Resource.Status.LOADING -> Log.e("List of my links", it.data.toString())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sett()
    }

    private operator fun Any.setValue(
        filmListFragment: FilmListFragment,
        property: KProperty<*>,
        filmListViewModel: FilmListViewModel
    ) {

    }
}