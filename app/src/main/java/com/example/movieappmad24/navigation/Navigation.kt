package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.com.example.movieappmad24.injectDependency.Injector
import com.example.movieappmad24.com.example.movieappmad24.viewmodels.ViewModelDetail
import com.example.movieappmad24.com.example.movieappmad24.viewmodels.ViewModelMoviesHome
import com.example.movieappmad24.com.example.movieappmad24.viewmodels.ViewModelMoviesWatchlist
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModelFactory = Injector.provideMoviesViewModelFactory(context)
    val viewModelDetail: ViewModelDetail = viewModel(factory = viewModelFactory)
    val homeViewModel: ViewModelMoviesHome = viewModel(factory = viewModelFactory)
    val watchlistViewModel: ViewModelMoviesWatchlist = viewModel(factory = viewModelFactory)

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) { type = NavType.StringType })
        ) {
            val movieId = it.arguments?.getString(DETAIL_ARGUMENT_KEY)
            DetailScreen(navController, movieId, viewModelDetail)
        }
        composable(route = Screen.WatchlistScreen.route) {
            WatchlistScreen(navController, watchlistViewModel)
        }
    }
}