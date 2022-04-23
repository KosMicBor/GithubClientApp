package kosmicbor.githubclientapp.data.usacases

import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.AddNewLocalUserUseCase

class AddNewLocalUserUseCaseImpl(private val repository: GitHubRepository):AddNewLocalUserUseCase {
    override fun addNewLocalUser(newUser: GithubUser) {
        repository.addUser(newUser)
    }
}