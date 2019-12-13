package testapp.ru.testapplication.di.modules

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import testapp.ru.testapplication.di.services.*
import testapp.ru.testapplication.ui.itemMovie.ItemMovieViewModel
import testapp.ru.testapplication.ui.movies.MoviesListViewModel

@ExperimentalCoroutinesApi
val serviceModule: Module = module {
    useRetrofitService()
    useMovieService()
}

@ExperimentalCoroutinesApi
val viewModelModule: Module = module {
    viewModel { MoviesListViewModel(get()) }
    viewModel { (id: Int) -> ItemMovieViewModel(id, get()) }
}