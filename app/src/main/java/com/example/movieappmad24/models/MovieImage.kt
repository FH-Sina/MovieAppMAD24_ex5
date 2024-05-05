package com.example.movieappmad24.com.example.movieappmad24.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_images")
data class MovieImage(
    @PrimaryKey(autoGenerate = true)
    val movieImageId: Long = 0,
    val movieDbId: Long,
    val url: String
)