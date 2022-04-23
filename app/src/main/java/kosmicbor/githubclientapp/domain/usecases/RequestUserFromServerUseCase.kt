package kosmicbor.githubclientapp.domain.usecases

import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.utils.RequestCallback

interface RequestUserFromServerUseCase {
    fun requestUser(login: String, callback: RequestCallback<GithubUser>)
    fun clearDisposable()
}


