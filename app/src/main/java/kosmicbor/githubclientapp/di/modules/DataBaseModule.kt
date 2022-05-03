package kosmicbor.githubclientapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import kosmicbor.githubclientapp.data.room.LocalUserDao
import kosmicbor.githubclientapp.data.room.LocalUserDataBase
import kosmicbor.githubclientapp.di.qualifiers.AppContext
import kosmicbor.githubclientapp.di.qualifiers.DataBaseName
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope

@Module
class DataBaseModule(@AppContext val context: Context) {

    @Provides
    @GithubApplicationScope
    fun getLocalDataSource(database: LocalUserDataBase): LocalUserDao {
        return database.localUserDao()
    }

    @DataBaseName
    @Provides
    fun getDataBaseName(): String {
        return "LocalUser.db"
    }

    @Provides
    @GithubApplicationScope
    fun getDataBase(@DataBaseName dataBaseName: String): LocalUserDataBase {
        return Room.databaseBuilder(
            context,
            LocalUserDataBase::class.java,
            dataBaseName
        ).build()
    }
}