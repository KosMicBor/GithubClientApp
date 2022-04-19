package kosmicbor.githubclientapp.data.room

import io.reactivex.rxjava3.core.Single
import kosmicbor.githubclientapp.domain.GithubUserEntity
import kosmicbor.githubclientapp.domain.LocalUserRepository
import kosmicbor.githubclientapp.utils.convertEntityToUsersList
import kosmicbor.githubclientapp.utils.convertGithubUserEntityToLocalEntity
import kosmicbor.githubclientapp.utils.convertLocalEntityToGithubUserEntity

class LocalUserRepoImpl(private val localDataSource: LocalUserDao) : LocalUserRepository {

    override fun getUsersList(): Single<List<GithubUserEntity>> {
        return Single.create { emitter ->

            emitter.onSuccess(convertEntityToUsersList(localDataSource.getAllUsers()))
        }
    }

    override fun getUser(login: String): Single<GithubUserEntity> {
        return Single.create { emitter ->

            emitter.onSuccess(
                convertLocalEntityToGithubUserEntity(
                    localDataSource.getLocalUser(
                        login
                    )
                )
            )
        }
    }

    override fun addUser(user: GithubUserEntity) {
        localDataSource.insertNewLocalUser(convertGithubUserEntityToLocalEntity(user))
    }

    override fun updateUser(user: GithubUserEntity) {
        localDataSource.updateCurrentLocalUser(convertGithubUserEntityToLocalEntity(user))
    }

    override fun deleteUser(user: GithubUserEntity) {
        localDataSource.deleteCurrentLocalUser(convertGithubUserEntityToLocalEntity(user))
    }
}