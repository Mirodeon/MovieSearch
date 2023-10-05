package com.mirodeon.moviesearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.adapter.SearchMovieAdapter
import com.mirodeon.moviesearch.databinding.FragmentContentSearchBinding
import com.mirodeon.moviesearch.network.dto.Movie
import com.mirodeon.moviesearch.viewModel.MovieViewModel
import com.mirodeon.moviesearch.viewModel.MovieViewModelFactory
import kotlinx.coroutines.launch

class ContentSearchFragment(private val input: String) : Fragment() {
    private var binding: FragmentContentSearchBinding? = null
    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory()
    }
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        recyclerView = binding?.containerRecyclerSearchedMovie
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val itemAdapter = SearchMovieAdapter { goToDetails(it) }
        recyclerView?.adapter = itemAdapter
        viewModel.getSearchMovie(input) { movies ->
            itemAdapter.submitList(movies)
        }
    }

    private fun goToDetails(movie: Movie) {
        val directions =
            SearchFragmentDirections.actionSearchFragmentToDetailsFragment(movie)
        findNavController().navigate(directions)
    }
}