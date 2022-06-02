package kosmicbor.githubclientapp.data.usacases

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.GithubUserRepo
import kosmicbor.githubclientapp.domain.usecases.ProfileScreenUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class ProfileScreenUseCaseImpl(private val repository: GitHubRepository) : ProfileScreenUseCase {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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
    }

    override fun requestUserReposFromServer(
        login: String,
        callback: RequestCallback<List<GithubUserRepo>>
    ) {
        compositeDisposable.add(
            repository.getUserReposRequest(login)
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
}