package testapp.ru.testapplication.di.services

import org.koin.core.module.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface IRetrofitService{
    val apiInterface: IRetrofitAPI
}

fun Module.useRetrofitService() {
    single<IRetrofitService>(createdAtStart = true) { RetrofitService() }
}

class RetrofitService : IRetrofitService {

    private val baseUrl = "https://s3-eu-west-1.amazonaws.com"

    override val apiInterface: IRetrofitAPI by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IRetrofitAPI::class.java)
    }
}