package com.mirodeon.moviesearch.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.adapter.SearchMovieAdapter
import com.mirodeon.moviesearch.adapter.TrendMovieAdapter
import com.mirodeon.moviesearch.databinding.FragmentContentSearchBinding
import com.mirodeon.moviesearch.databinding.FragmentTrendBinding
import com.mirodeon.moviesearch.network.dto.Movie
import com.mirodeon.moviesearch.viewModel.MovieViewModel
import com.mirodeon.moviesearch.viewModel.MovieViewModelFactory

class TrendFragment : Fragment() {
    private var binding: FragmentTrendBinding? = null
    private val viewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory()
    }
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendBinding.inflate(inflater, container, false)
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
        recyclerView = binding?.containerRecyclerTrendMovie
        recyclerView?.layoutManager = GridLayoutManager(activity, 3)
        val itemAdapter = TrendMovieAdapter { goToDetails(it) }
        recyclerView?.adapter = itemAdapter
        viewModel.getTrendMovie { movies ->
            itemAdapter.submitList(movies)
        }
    }

    private fun goToDetails(movie: Movie) {
        val directions =
            TrendFragmentDirections.actionTrendFragmentToDetailsFragment(movie)
        findNavController().navigate(directions)
    }

}