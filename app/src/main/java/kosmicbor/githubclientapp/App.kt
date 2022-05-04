package kosmicbor.githubclientapp

import android.app.Application
import android.content.Context
import kosmicbor.githubclientapp.di.AppComponent
import kosmicbor.githubclientapp.di.DaggerAppComponent
import kosmicbor.githubclientapp.di.modules.DataBaseModule
import kosmicbor.githubclientapp.di.modules.GithubApiModule
import kosmicbor.githubclientapp.di.modules.GithubRepositoryModule
import kosmicbor.githubclientapp.di.modules.HttpClientModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .dataBaseModule(DataBaseModule(this))
            .githubApiModule(GithubApiModule())
            .githubRepositoryModule(GithubRepositoryModule(this))
            .httpClientModule(HttpClientModule())
            .build()
    }


}

val Context.app: App
    get() {
        return applicationContext as App
    }