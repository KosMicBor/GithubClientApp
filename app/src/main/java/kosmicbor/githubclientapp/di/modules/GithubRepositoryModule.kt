package kosmicbor.githubclientapp.di.modules

import dagger.Module
import dagger.Provides
import kosmicbor.githubclientapp.data.GithubRepositoryImpl
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDao
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope
import kosmicbor.githubclientapp.domain.GitHubRepository

@Module(includes = [GithubApiModule::class, DataBaseModule::class])
class GithubRepositoryModule {

    @Provides
    @GithubApplicationScope
    fun getGithubRepository(api: GithubApi, localDataSource: LocalUserDao): GitHubRepository {
        return GithubRepositoryImpl(api, localDataSource)
    }
}