package kosmicbor.githubclientapp.data.usacases

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.LoginScreenUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class LoginScreenUseCaseImpl(private val repository: GitHubRepository) : LoginScreenUseCase {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun addNewLocalUser(newUser: GithubUser) {
        repository.addUser(newUser)
    }

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

    override fun requestUser(login: String, callback: RequestCallback<GithubUser>) {
        compositeDisposable.add(
            repository
                .getUserRequest(login)
                .subscribeOn(Schedulers.single())
                .subscribeBy(
                    onSuccess = {
                        callback.onSuccess(it)
                    },
                    onError = {
                        callback.onError(it)
                    }
                )
        )
    }

    override fun clearDisposable() {
        compositeDisposable.clear()
        repository.clearDisposible()
    }

    override fun deleteLocalUser(githubUser: GithubUser) {
        repository.deleteUser(githubUser)
    }
}