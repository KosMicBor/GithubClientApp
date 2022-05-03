package kosmicbor.githubclientapp.di

import dagger.Component
import kosmicbor.githubclientapp.di.modules.*
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.ui.loginscreen.LoginFragment
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileFragment

@GithubApplicationScope
@Component(
    modules = [
        DataBaseModule::class,
        GithubApiModule::class,
        GithubRepositoryModule::class,
        HttpClientModule::class,
        UseCasesModule::class
    ]
)
interface AppComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(profileFragment: ProfileFragment)

    fun getGithubRepository(): GitHubRepository
}