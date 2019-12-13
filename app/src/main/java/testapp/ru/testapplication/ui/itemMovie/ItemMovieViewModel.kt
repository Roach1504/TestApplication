package testapp.ru.testapplication.ui.itemMovie

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import testapp.ru.testapplication.di.services.IMovieService

@ExperimentalCoroutinesApi
class ItemMovieViewModel(private val itemId: Int,
                         private val movieService: IMovieService):ViewModel() {

    fun getItem() =  movieService.getItemMovie(itemId)
}