package kosmicbor.githubclientapp.domain.usecases

import kosmicbor.githubclientapp.domain.GithubUser

interface AddNewLocalUserUseCase {
    fun addNewLocalUser(newUser: GithubUser)
}