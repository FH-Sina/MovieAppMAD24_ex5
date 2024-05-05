package com.example.movieappmad24.com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.com.example.movieappmad24.otherInterfaces.ViewModelFunctions
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelDetail(private val repository: MovieRepository) : ViewModel(), ViewModelFunctions {
    private val _movie = MutableStateFlow<MovieWithImages?>(null)
    val movie: StateFlow<MovieWithImages?> = _movie.asStateFlow()

    fun getMovieById(movieId: String) {
        movieId.toLongOrNull()?.let { id ->
            viewModelScope.launch {
                repository.getById(id).collect { movieData ->
                    _movie.value = movieData
                }
            }
        }
    }

    override fun updateFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }
}