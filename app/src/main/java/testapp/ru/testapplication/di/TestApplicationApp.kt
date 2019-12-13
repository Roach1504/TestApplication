package testapp.ru.testapplication.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import testapp.ru.testapplication.di.modules.*

class TestApplicationApp: Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplicationApp)
            modules(serviceModule, viewModelModule)
        }
    }
}