package com.example.movieappmad24.com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.com.example.movieappmad24.otherInterfaces.ViewModelMovies
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ViewModelMoviesHome(private val repository: MovieRepository) : ViewModel(), ViewModelMovies {
    private val _movies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    override val movies: StateFlow<List<MovieWithImages>> = _movies.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged().collect { movies ->
                _movies.value = movies
                Log.i("HomeViewModel", "Movies loaded successfully.")
            }
        }
    }

    override fun updateFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
            Log.i("HomeViewModel", "Favorite status updated for movie: ${movie.title}")
        }
    }
}