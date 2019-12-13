package testapp.ru.testapplication.di.services

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import org.koin.core.module.Module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import testapp.ru.testapplication.dataModels.ConnectionState
import testapp.ru.testapplication.dataModels.MovieItem
import testapp.ru.testapplication.dataModels.MovieList


@ExperimentalCoroutinesApi
interface IMovieService {
    val movieListChannel: BroadcastChannel<List<MovieItem>>
    val connectionChannel: BroadcastChannel<ConnectionState>
    fun getItemMovie(id:Int): MovieItem?
}

@ExperimentalCoroutinesApi
fun Module.useMovieService() {
    single<IMovieService>(createdAtStart = true) { MovieService(get()) }
}

@ExperimentalCoroutinesApi
class MovieService(retrofitService: IRetrofitService) : IMovieService {

    override val movieListChannel: BroadcastChannel<List<MovieItem>> =
        BroadcastChannel(Channel.CONFLATED)

    override val connectionChannel: BroadcastChannel<ConnectionState> =
        BroadcastChannel(Channel.CONFLATED)

    private val movies = hashMapOf<Int, MovieItem>()

    override fun getItemMovie(id: Int): MovieItem? = movies[id]


    init {
        retrofitService.apiInterface.getMovieList().enqueue(object : Callback<MovieList> {

            override fun onFailure(call: Call<MovieList>?, t: Throwable?) {
                connectionChannel.offer(ConnectionState.Failure)
            }

            override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {
                if (response?.code() == 200) {
                    val body = response.body()
                    body.films.forEach { mouvie ->
                        mouvie.id?.let {
                            movies[it] = mouvie
                        }
                    }
                    movieListChannel.offer(body.films)
                    connectionChannel.offer(ConnectionState.Success)
                } else connectionChannel.offer(ConnectionState.BadResponse)
            }
        })
    }
}