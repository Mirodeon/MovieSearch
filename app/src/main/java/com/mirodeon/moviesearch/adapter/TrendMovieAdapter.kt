package com.mirodeon.moviesearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirodeon.moviesearch.R
import com.mirodeon.moviesearch.databinding.SimilarMovieCellBinding
import com.mirodeon.moviesearch.databinding.TrendMovieCellBinding
import com.mirodeon.moviesearch.network.dto.Movie
import com.mirodeon.moviesearch.network.utils.UrlApi
import com.squareup.picasso.Picasso

class TrendMovieAdapter(
    private val onItemClicked: (item: Movie) -> Unit
) : ListAdapter<Movie, TrendMovieAdapter.TrendMovieViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendMovieViewHolder {
        val viewHolder = TrendMovieViewHolder(
            TrendMovieCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TrendMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrendMovieViewHolder(
        private var binding: TrendMovieCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            setupImage(UrlApi.imageMovieApi + movie.poster)
            binding.txtVoteTrendMovie.text = movie.vote.toString()
        }

        private fun setupImage(url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .fit()
                .centerCrop()
                .into(binding.imgMovieTrend)
        }

    }
}