package kosmicbor.githubclientapp.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Single<List<GithubUserRepoDto>>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String?): Single<GithubUserDto>
}