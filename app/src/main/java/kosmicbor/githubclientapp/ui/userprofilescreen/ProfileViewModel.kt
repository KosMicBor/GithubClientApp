package kosmicbor.githubclientapp.ui.userprofilescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.GithubUserEntity
import kosmicbor.githubclientapp.domain.GithubUserRepoEntity

class ProfileViewModel(private val repo: GitHubRepository) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<GithubUserRepoEntity>>()
    val reposLiveData = _reposLiveData

    private val _userLiveData = MutableLiveData<GithubUserEntity>()
    val userLiveData: LiveData<GithubUserEntity> = _userLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUserRepos(login: String) {

        compositeDisposable.add(
            repo.getUserReposRequest(login)
                .subscribeOn(Schedulers.single())
                .subscribeBy(
                    onSuccess = {
                        _reposLiveData.postValue(it)
                    },

                    onError = {
                        _errorLiveData.postValue(it.localizedMessage)
                    }

                )
        )
    }

    fun getCurrentUser(login: String) {
        _loadingLiveData.postValue(true)

        compositeDisposable.add(
            repo
                .getUserRequest(login)
                .subscribeOn(Schedulers.single())
                .subscribeBy(
                    onSuccess = {
                        _loadingLiveData.postValue(false)
                        _userLiveData.postValue(it)
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

}