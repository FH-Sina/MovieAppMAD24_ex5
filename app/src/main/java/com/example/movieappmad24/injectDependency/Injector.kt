package com.example.movieappmad24.com.example.movieappmad24.injectDependency


import android.content.Context
import com.example.movieappmad24.com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.com.example.movieappmad24.viewmodels.ViewModelMoviesFactory

object Injector {
    private fun provideMovieRepository(context: Context): MovieRepository {
        val database = MovieDB.getDatabase(context.applicationContext)
        return MovieRepository.getInstance(database.movieDao())
    }

    fun provideMoviesViewModelFactory(context: Context): ViewModelMoviesFactory {
        val repository = provideMovieRepository(context)
        return ViewModelMoviesFactory(repository = repository)
    }
}