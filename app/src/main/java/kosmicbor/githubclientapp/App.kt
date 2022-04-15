package kosmicbor.githubclientapp

import android.app.Application
import android.content.Context
import kosmicbor.githubclientapp.data.MockGithubRepositoryImpl

class App : Application() {
    val githubRepo: MockGithubRepositoryImpl by lazy {
        MockGithubRepositoryImpl()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }