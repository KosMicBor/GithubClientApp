package kosmicbor.githubclientapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import kosmicbor.githubclientapp.data.retrofit.RetrofitGithubImpl
import kosmicbor.githubclientapp.data.room.LocalUserDataBase
import kosmicbor.githubclientapp.data.room.LocalUserRepoImpl
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.LocalUserRepository

class App : Application() {
    val githubRepo: GitHubRepository by lazy {
        RetrofitGithubImpl()
    }

    val localRepo: LocalUserRepository by lazy {
        val db = Room.databaseBuilder(
            applicationContext,
            LocalUserDataBase::class.java, DATABASE_NAME
        ).build()

        LocalUserRepoImpl(db.localUserDao())
    }

    companion object {
        private const val DATABASE_NAME = "LocalUser.db"
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }