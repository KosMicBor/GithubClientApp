package kosmicbor.githubclientapp.domain

import io.reactivex.rxjava3.core.Single

interface GitHubRepository {
    fun getUserRequest(login: String): Single<GithubUser>
    fun getUserReposRequest(userLogin: String): Single<List<GithubUserRepo>>
    fun getUsersList(): Single<List<GithubUser>>
    fun getUser(login: String): Single<GithubUser>
    fun addUser(user: GithubUser)
    fun updateUser(user: GithubUser)
    fun deleteUser(user: GithubUser)
    fun clearDisposible()
}