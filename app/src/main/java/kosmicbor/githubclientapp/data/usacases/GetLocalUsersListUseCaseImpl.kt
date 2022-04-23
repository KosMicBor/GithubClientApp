package kosmicbor.githubclientapp.data.usacases

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.GetLocalUsersListUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class GetLocalUsersListUseCaseImpl(private val repository: GitHubRepository) :
    GetLocalUsersListUseCase {
    override fun getLocalUsersList(callback: RequestCallback<List<GithubUser>>) {
        repository.getUsersList()
            .subscribeOn(Schedulers.single())
            .subscribeBy(
                onSuccess = {
                    callback.onSuccess(it)
                },

                onError = {
                    callback.onError(it)
                }
            )
    }
}