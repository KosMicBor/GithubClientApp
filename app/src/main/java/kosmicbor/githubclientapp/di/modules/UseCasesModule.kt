package kosmicbor.githubclientapp.di.modules

import dagger.Module
import dagger.Provides
import kosmicbor.githubclientapp.data.usacases.LoginScreenUseCaseImpl
import kosmicbor.githubclientapp.data.usacases.ProfileScreenUseCaseImpl
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.usecases.LoginScreenUseCase
import kosmicbor.githubclientapp.domain.usecases.ProfileScreenUseCase

@Module(includes = [GithubRepositoryModule::class])
class UseCasesModule {

    @Provides
    fun getLoginScreenUseCase(repo: GitHubRepository): LoginScreenUseCase {
        return LoginScreenUseCaseImpl(repo)
    }

    @Provides
    fun getProfileScreenUseCase(repo: GitHubRepository): ProfileScreenUseCase {
        return ProfileScreenUseCaseImpl(repo)
    }
}