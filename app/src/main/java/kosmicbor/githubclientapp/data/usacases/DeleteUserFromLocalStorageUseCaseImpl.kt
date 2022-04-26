package kosmicbor.githubclientapp.data.usacases

import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.DeleteUserFromLocalStorageUseCase

class DeleteUserFromLocalStorageUseCaseImpl(private val repository: GitHubRepository) :
    DeleteUserFromLocalStorageUseCase {
    override fun deleteLocalUser(githubUser: GithubUser) {
        repository.deleteUser(githubUser)
    }
}