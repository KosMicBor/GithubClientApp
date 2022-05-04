package kosmicbor.githubclientapp.domain.usecases

import kosmicbor.githubclientapp.domain.GithubUser

interface DeleteUserFromLocalStorageUseCase {
    fun deleteLocalUser(githubUser: GithubUser)
}