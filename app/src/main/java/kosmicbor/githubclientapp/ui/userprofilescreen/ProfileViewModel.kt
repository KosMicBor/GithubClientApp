package kosmicbor.githubclientapp.ui.userprofilescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.GithubUserRepo
import kosmicbor.githubclientapp.domain.usecases.RequestUserFromServerUseCase
import kosmicbor.githubclientapp.domain.usecases.RequestUserReposFromServerUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class ProfileViewModel(
    private val requestUserFromServerUseCase: RequestUserFromServerUseCase,
    private val requestUserReposFromServerUseCase: RequestUserReposFromServerUseCase,
) : ViewModel() {

    private var _savedLogin = ""
    val savedLogin: String
        get() = _savedLogin

    private val _reposLiveData = MutableLiveData<List<GithubUserRepo>>()
    val reposLiveData: LiveData<List<GithubUserRepo>> = _reposLiveData

    private val _userLiveData = MutableLiveData<GithubUser>()
    val userLiveDataDTO: LiveData<GithubUser> = _userLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    fun getUserRepos(login: String) {

        requestUserReposFromServerUseCase.requestUserReposFromServer(
            login,
            object : RequestCallback<List<GithubUserRepo>> {
                override fun onSuccess(value: List<GithubUserRepo>) {
                    _reposLiveData.postValue(value)
                }

                override fun onError(t: Throwable) {
                    _errorLiveData.postValue(t.localizedMessage)
                }

            })
    }

    fun getCurrentUser(login: String) {
        _loadingLiveData.postValue(true)

        requestUserFromServerUseCase.requestUser(login, object : RequestCallback<GithubUser> {
            override fun onSuccess(value: GithubUser) {
                _loadingLiveData.postValue(false)
                _userLiveData.postValue(value)
            }

            override fun onError(t: Throwable) {
                _loadingLiveData.postValue(false)
                _errorLiveData.postValue(t.localizedMessage)
            }

        })
    }

    fun saveLogin(userLogin: String) {
        _savedLogin = userLogin
    }

    override fun onCleared() {
        requestUserFromServerUseCase.clearDisposable()
        requestUserReposFromServerUseCase.clearDisposable()
        super.onCleared()
    }
}