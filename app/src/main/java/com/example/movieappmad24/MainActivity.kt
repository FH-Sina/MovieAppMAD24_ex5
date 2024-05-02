package com.example.movieappmad24

import android.os.Bundle
<<<<<<< HEAD
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
=======
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieappmad24.navigation.Navigation
>>>>>>> origin/exercise-05-starter
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
<<<<<<< HEAD
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Card {
                        Image(painter = painterResource(id = R.drawable.movie_image), contentDescription = "placeholder_image")
                    }
                }
            }
        }
    }
}
=======
                Navigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called.")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume called.")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause called.")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called.")
    }
}

>>>>>>> origin/exercise-05-starter
