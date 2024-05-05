package com.example.movieappmad24.com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.example.movieappmad24.com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao{
    @Insert
    suspend fun addMovie(movie: Movie)

    @Insert
    suspend fun addImages(movieImage: MovieImage)

    @Delete
    suspend fun delete(movie: Movie)
    @Update
    suspend fun update(movie: Movie)



    @Query("SELECT * from movie where dbId=:id")
    fun get(id: Long): Flow<MovieWithImages>

    @Query("SELECT * from movie")
    fun getAll(): Flow<List<MovieWithImages>>

    @Query("SELECT * from movie where isFavorite= 1")
    fun getFavorites(): Flow<List<MovieWithImages>>

}