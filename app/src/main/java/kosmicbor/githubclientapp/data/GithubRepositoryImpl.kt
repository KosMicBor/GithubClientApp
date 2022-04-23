package kosmicbor.githubclientapp.data

import io.reactivex.rxjava3.core.Single
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDao
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.GithubUserRepo
import kosmicbor.githubclientapp.utils.convertGithubUserToLocalUserEntity
import kosmicbor.githubclientapp.utils.convertLocalUserEntityToGithubUser
import kosmicbor.githubclientapp.utils.convertLocalUsersListToUsersList
import kosmicbor.githubclientapp.utils.convertUserDtoToGithubUser

class GithubRepositoryImpl(private val api: GithubApi, private val localDataSource: LocalUserDao) :
    GitHubRepository {

    override fun getUserRequest(login: String): Single<GithubUser> {
        return api.getUser(login).map {
            convertUserDtoToGithubUser(it)
        }
    }

    override fun getUserReposRequest(userLogin: String): Single<List<GithubUserRepo>> {
        return api.listRepos(userLogin).map {
            it.map { githubUserRepoEntity ->
                GithubUserRepo(
                    id = githubUserRepoEntity.id,
                    nodeId = githubUserRepoEntity.nodeId,
                    name = githubUserRepoEntity.name,
                    description = githubUserRepoEntity.description,
                    isPrivate = githubUserRepoEntity.isPrivate,
                    url = githubUserRepoEntity.url
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
                convertLocalUserEntityToGithubUser(
                    localDataSource.getLocalUser(
                        login
                    )
                )
            )
        }
    }

    override fun addUser(user: GithubUser) {
        localDataSource.insertNewLocalUser(convertGithubUserToLocalUserEntity(user))
    }

    override fun updateUser(user: GithubUser) {
        localDataSource.updateCurrentLocalUser(convertGithubUserToLocalUserEntity(user))
    }

    override fun deleteUser(user: GithubUser) {
        localDataSource.deleteCurrentLocalUser(convertGithubUserToLocalUserEntity(user))
    }
}