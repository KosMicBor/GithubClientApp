package kosmicbor.githubclientapp

import android.app.Application
import android.content.Context
import kosmicbor.githubclientapp.data.retrofit.RetrofitGithubImpl
import kosmicbor.githubclientapp.domain.GitHubRepository

class App : Application() {
    val githubRepo: GitHubRepository by lazy {
        RetrofitGithubImpl()
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }