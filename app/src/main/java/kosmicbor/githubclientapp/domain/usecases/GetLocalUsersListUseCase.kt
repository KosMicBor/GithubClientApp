package kosmicbor.githubclientapp.domain.usecases

import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.utils.RequestCallback

interface GetLocalUsersListUseCase {
    fun getLocalUsersList(callback: RequestCallback<List<GithubUser>>)
}