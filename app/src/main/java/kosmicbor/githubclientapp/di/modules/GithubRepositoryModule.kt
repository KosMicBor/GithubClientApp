package kosmicbor.githubclientapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import kosmicbor.githubclientapp.data.GithubRepositoryImpl
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDao
import kosmicbor.githubclientapp.di.qualifiers.AppContext
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.utils.ResourcesHelper

@Module(includes = [GithubApiModule::class, DataBaseModule::class])
class GithubRepositoryModule(@AppContext val context: Context) {

    @Provides
    @GithubApplicationScope
    fun getGithubRepository(api: GithubApi, localDataSource: LocalUserDao): GitHubRepository {
        return GithubRepositoryImpl(api, localDataSource)
    }

    @Provides
    @GithubApplicationScope
    fun getResourcesHelper(): ResourcesHelper {
        return ResourcesHelper(context)
    }
}