package com.example.movieappmad24.com.example.movieappmad24.otherInterfaces



import com.example.movieappmad24.com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.StateFlow

interface ViewModelMovies: ViewModelFunctions {
    val movies: StateFlow<List<MovieWithImages>>
}