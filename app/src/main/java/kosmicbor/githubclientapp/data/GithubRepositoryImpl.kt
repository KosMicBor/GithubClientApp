package kosmicbor.githubclientapp.data

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDao
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.GithubUserRepo
import kosmicbor.githubclientapp.utils.convertGithubUserDtoToGithubUser
import kosmicbor.githubclientapp.utils.convertGithubUserToLocalUserDto
import kosmicbor.githubclientapp.utils.convertLocalUserDtoToGithubUser
import kosmicbor.githubclientapp.utils.convertLocalUsersListToUsersList

class GithubRepositoryImpl(private val api: GithubApi, private val localDataSource: LocalUserDao) :
    GitHubRepository {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getUserRequest(login: String): Single<GithubUser> {
        return api.getUser(login).map {
            convertGithubUserDtoToGithubUser(it)
        }
    }

    override fun getUserReposRequest(userLogin: String): Single<List<GithubUserRepo>> {
        return api.listRepos(userLogin).map {
            it.map { githubUserRepoEntity ->
                GithubUserRepo(
                    name = githubUserRepoEntity.name
                )
            }
        }
    }

    override fun getUsersList(): Single<List<GithubUser>> {
        return Single.create { emitter ->

            emitter.onSuccess(convertLocalUsersListToUsersList(localDataSource.getAllUsers()))
        }
    }

    override fun getUser(login: String): Single<GithubUser> {
        return Single.create { emitter ->

            emitter.onSuccess(
                convertLocalUserDtoToGithubUser(
                    localDataSource.getLocalUser(
                        login
                    )
                )
            )
        }
    }

    override fun addUser(user: GithubUser) {
        localDataSource.insertNewLocalUser(convertGithubUserToLocalUserDto(user))
    }

    override fun updateUser(user: GithubUser) {
        localDataSource.updateCurrentLocalUser(convertGithubUserToLocalUserDto(user))
    }

    override fun deleteUser(user: GithubUser) {

        compositeDisposable.add (
            localDataSource.deleteCurrentLocalUser(convertGithubUserToLocalUserDto(user))
                .subscribeOn(Schedulers.single()).subscribe()
        )
    }

    override fun clearDisposible() {
        compositeDisposable.clear()
    }


}