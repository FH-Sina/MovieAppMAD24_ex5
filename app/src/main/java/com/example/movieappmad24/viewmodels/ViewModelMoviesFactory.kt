package com.example.movieappmad24.com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.com.example.movieappmad24.data.MovieRepository

class ViewModelMoviesFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ViewModelMoviesHome::class.java) -> ViewModelMoviesHome(repository = repository) as T
        modelClass.isAssignableFrom(ViewModelDetail::class.java) -> ViewModelDetail(repository = repository) as T
        modelClass.isAssignableFrom(ViewModelMoviesWatchlist::class.java) -> ViewModelMoviesWatchlist(repository = repository) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}