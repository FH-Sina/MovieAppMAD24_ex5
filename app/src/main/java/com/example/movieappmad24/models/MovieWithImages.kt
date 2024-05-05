package com.example.movieappmad24.com.example.movieappmad24.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.com.example.movieappmad24.models.MovieImage

data class MovieWithImages(
    @Embedded
    val movie: Movie,

    @Relation(
        parentColumn = "databaseId",
        entityColumn = "movieDatabaseId",
        entity = MovieImage::class
    )
    val movieImages: List<MovieImage>
)