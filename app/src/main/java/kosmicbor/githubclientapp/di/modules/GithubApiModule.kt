package kosmicbor.githubclientapp.di.modules

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.di.qualifiers.RetrofitBaseUrl
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [HttpClientModule::class])
class GithubApiModule {

    @Provides
    @GithubApplicationScope
    fun getGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @GithubApplicationScope
    fun getRetrofit(
        @RetrofitBaseUrl baseUrl: String,
        callAdapterFactory: RxJava3CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @RetrofitBaseUrl
    @Provides
    fun getApiUrl(): String {
        return "https://api.github.com/"
    }

    @Provides
    fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun getRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }
}