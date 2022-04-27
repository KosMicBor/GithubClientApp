package kosmicbor.githubclientapp.domain.usecases

import kosmicbor.githubclientapp.domain.GithubUserRepo
import kosmicbor.githubclientapp.utils.RequestCallback

interface RequestUserReposFromServerUseCase {
    fun requestUserReposFromServer(login: String, callback: RequestCallback<List<GithubUserRepo>>)
    fun clearDisposable()
}