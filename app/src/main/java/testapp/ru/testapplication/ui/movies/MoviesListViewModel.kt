package testapp.ru.testapplication.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import testapp.ru.testapplication.di.services.IMovieService

@ExperimentalCoroutinesApi
class MoviesListViewModel(private val movieService: IMovieService) : ViewModel() {

    val movieList = liveData {
        movieService.movieListChannel.openSubscription().consumeEach {
            emit(it)
        }
    }
}