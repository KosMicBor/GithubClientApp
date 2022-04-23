package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.App
import kosmicbor.githubclientapp.data.room.LocalUserRepoImpl
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUserEntity
import kosmicbor.githubclientapp.domain.LocalUserRepository

class LoginViewModel(
    private val userRepo: GitHubRepository,
    private val localRepo: LocalUserRepository
) : ViewModel() {

    private val _usersListLiveData = MutableLiveData<List<GithubUserEntity>>()
    val usersListLiveData: LiveData<List<GithubUserEntity>> = _usersListLiveData

    private val _userLiveData = MutableLiveData<GithubUserEntity>()
    val userLiveData: LiveData<GithubUserEntity> = _userLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addNewUser(login: String = "") {
        _loadingLiveData.postValue(true)

        compositeDisposable.add(
            userRepo
                .getUserRequest(login)
                .subscribeOn(Schedulers.single())
                .subscribeBy(
                    onSuccess = {
                        _loadingLiveData.postValue(false)
                        _userLiveData.postValue(it)
                        localRepo.addUser(it)
                    },
                    onError = {
                        _loadingLiveData.postValue(false)
                        _errorLiveData.postValue(it.localizedMessage)
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getUsersList() {
        localRepo.getUsersList()
            .subscribeOn(Schedulers.single())
            .subscribeBy(
                onSuccess = {
                    _usersListLiveData.postValue(it)
                }
            )
    }
}