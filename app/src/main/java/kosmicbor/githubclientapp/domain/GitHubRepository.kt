package kosmicbor.githubclientapp.domain

import io.reactivex.rxjava3.core.Single

interface GitHubRepository {
    fun getUsersListRequest(): List<GithubUserEntity>
    fun getUserRequest(login: String): Single<GithubUserEntity>
    fun getUserReposRequest(userLogin: String): Single<List<GithubUserRepoEntity>>
}