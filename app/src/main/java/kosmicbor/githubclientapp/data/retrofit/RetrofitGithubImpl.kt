package kosmicbor.githubclientapp.data.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import kosmicbor.githubclientapp.domain.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitGithubImpl : GitHubRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GithubApi::class.java)

    override fun getUserRequest(login: String): Single<GithubUserEntity> {
        return api.getUser(login)
    }

    override fun getUserReposRequest(userLogin: String): Single<List<GithubUserRepoEntity>> {
        return api.listRepos(userLogin)
    }
}