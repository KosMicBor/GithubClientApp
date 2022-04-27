package kosmicbor.githubclientapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kosmicbor.githubclientapp.data.GithubRepositoryImpl
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDataBase
import kosmicbor.githubclientapp.domain.GitHubRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        private const val DATABASE_NAME = "LocalUser.db"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GithubApi::class.java)

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            LocalUserDataBase::class.java, DATABASE_NAME
        ).build()
    }

    val githubRepo: GitHubRepository by lazy {
        GithubRepositoryImpl(api, db.localUserDao())
    }
}


val Context.app: App
    get() {
        return applicationContext as App
    }