package testapp.ru.testapplication.di.services

import retrofit2.Call
import retrofit2.http.GET
import testapp.ru.testapplication.dataModels.MovieList

interface IRetrofitAPI {

    @GET("/sequeniatesttask/films.json")
    fun getMovieList(): Call<MovieList>
}