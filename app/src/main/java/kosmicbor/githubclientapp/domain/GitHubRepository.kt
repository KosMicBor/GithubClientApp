package kosmicbor.githubclientapp.domain

import kosmicbor.githubclientapp.data.MockGithubRepositoryImpl

interface GitHubRepository {
    fun getUsersListRequest(): List<User>
    fun getUserRequest(id: Int, onGetUserListener: MockGithubRepositoryImpl.OnGetUserListener)
}