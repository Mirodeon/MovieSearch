package com.mirodeon.moviesearch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mirodeon.moviesearch.network.dto.Movie
import com.mirodeon.moviesearch.network.service.MovieServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieViewModel : ViewModel() {
    private val movieService = MovieServiceImpl()

    fun getSearchMovie(query: String, handler: (data: List<Movie>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieService.getSearchMovie(query)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body()?.results
                        handler(res)
                    }
                } catch (e: HttpException) {
                    print(e)
                } catch (e: Throwable) {
                    print(e)
                }
            }
        }
    }

    fun getSimilarMovie(id: String, handler: (data: List<Movie>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieService.getSimilarMovie(id)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body()?.results
                        handler(res)
                    }
                } catch (e: HttpException) {
                    print(e)
                } catch (e: Throwable) {
                    print(e)
                }
            }
        }
    }

    fun getTrendMovie(handler: (data: List<Movie>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieService.getTrendMovie()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val res = response.body()?.results
                        handler(res)
                    }
                } catch (e: HttpException) {
                    print(e)
                } catch (e: Throwable) {
                    print(e)
                }
            }
        }
    }

}

class MovieViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}