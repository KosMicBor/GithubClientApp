package kosmicbor.githubclientapp.di

import androidx.room.Room
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kosmicbor.githubclientapp.data.GithubRepositoryImpl
import kosmicbor.githubclientapp.data.retrofit.GithubApi
import kosmicbor.githubclientapp.data.room.LocalUserDataBase
import kosmicbor.githubclientapp.data.usacases.*
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.usecases.*
import kosmicbor.githubclientapp.ui.loginscreen.LoginViewModel
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<GitHubRepository> { GithubRepositoryImpl(get(), get()) }
    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }

    //Retrofit
    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<CallAdapter.Factory> { RxJava3CallAdapterFactory.create() }
    single(named("github_api_url")) { "https://api.github.com/" }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("github_api_url")))
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .build()
    }

    //Room
    single(named("database_name")) { "LocalUser.db" }
    single {
        Room.databaseBuilder(
            androidContext(),
            LocalUserDataBase::class.java,
            get(named("database_name"))
        ).build()
    }
    single {
        val dataBase = get<LocalUserDataBase>()
        dataBase.localUserDao()
    }
}

val viewModelModule = module {
    factory<AddNewLocalUserUseCase> { AddNewLocalUserUseCaseImpl(get()) }
    factory<DeleteUserFromLocalStorageUseCase> { DeleteUserFromLocalStorageUseCaseImpl(get()) }
    factory<GetLocalUsersListUseCase> { GetLocalUsersListUseCaseImpl(get()) }
    factory<RequestUserReposFromServerUseCase> { RequestUserReposFromServerUseCaseImpl(get()) }
    factory<RequestUserFromServerUseCase> { RequestUserFromServerUseCaseImpl(get()) }

    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}