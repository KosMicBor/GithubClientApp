package kosmicbor.githubclientapp.domain

import io.reactivex.rxjava3.core.Single

interface LocalUserRepository {
    fun getUsersList(): Single<List<GithubUserEntity>>
    fun getUser(login: String): Single<GithubUserEntity>
    fun addUser(user: GithubUserEntity)
    fun updateUser(user: GithubUserEntity)
    fun deleteUser(user: GithubUserEntity)
}