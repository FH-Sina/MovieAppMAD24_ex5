package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.example.movieappmad24.com.example.movieappmad24.viewmodels.ViewModelDetail
import com.example.movieappmad24.widgets.HorizontalScrollableImageView
import com.example.movieappmad24.widgets.MovieRow
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun DetailScreen(
    navController: NavHostController,
    movieId: String?,
    viewModelDetail: ViewModelDetail
) {
    movieId?.let { id ->
        viewModelDetail.getMovieById(id)
        val movieState by viewModelDetail.movie.collectAsState()
        movieState?.let { movieWithImage ->
            Scaffold(
                topBar = {
                    SimpleTopAppBar(title = movieWithImage.movie.title) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Go back"
                            )
                        }
                    }
                }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    MovieRow(
                        movieWithImages = movieWithImage,
                        onFavoriteClick = { viewModelDetail.updateFavorite(movieWithImage.movie) }
                    )

                    Divider(modifier = Modifier.padding(4.dp))

                    Text("Movie Trailer", modifier = Modifier.padding(horizontal = 16.dp))
                    movieWithImage.movie.trailer?.let { trailerURL ->
                        VideoPlayer(trailerURL = trailerURL)
                    }

                    Divider(modifier = Modifier.padding(4.dp))

                    HorizontalScrollableImageView(movieWithImages = movieWithImage)
                }
            }
        }
    }
}

@Composable
fun VideoPlayer(trailerURL: String) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(trailerURL))
            prepare()
            playWhenReady = true
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_RESUME -> exoPlayer.play()
                Lifecycle.Event.ON_PAUSE -> exoPlayer.pause()
                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f),
        factory = { PlayerView(context).apply { player = exoPlayer } },
        update = { it.onResume() }
    )
}