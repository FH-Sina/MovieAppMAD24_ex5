package com.example.movieappmad24.com.example.movieappmad24.functions

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import android.util.Log
import com.example.movieappmad24.com.example.movieappmad24.data.MovieDB
import com.example.movieappmad24.com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.com.example.movieappmad24.models.MovieImage

import com.example.movieappmad24.models.getMovies

class MovieDataSeederWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    private val dao = MovieDB.getDatabase(context = ctx).movieDao()
    private val repository = MovieRepository(movieDao = dao)

    override suspend fun doWork(): Result = try {
        seedMovies()
        Result.success()
    } catch (exception: Throwable) {
        logError(exception)
        Result.failure()
    }

    private suspend fun seedMovies() {
        val movies = getMovies()
        var movieDbID: Long = 1L
        movies.forEach { movie ->
            repository.addMovie(movie)
            movie.images.forEach { url ->
                repository.addMovieImages(MovieImage(movieDbId = movieDbID, url = url))
            }
            movieDbID++
        }
    }

    private fun logError(exception: Throwable) {
        Log.e("MovieDataSeederWorker", "Error seeding database", exception)
    }
}