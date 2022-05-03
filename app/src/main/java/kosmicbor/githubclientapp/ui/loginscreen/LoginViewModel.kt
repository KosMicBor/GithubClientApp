package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.LoginScreenUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class LoginViewModel(
    private val loginScreenUseCase: LoginScreenUseCase
) : ViewModel() {

    private val _usersListLiveData = MutableLiveData<List<GithubUser>>()
    val usersListLiveData: LiveData<List<GithubUser>> = _usersListLiveData

    private val _userLiveData = MutableLiveData<GithubUser>()
    val userLiveData: LiveData<GithubUser> = _userLiveData

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun addNewUser(login: String = "") {
        _loadingLiveData.postValue(true)

        loginScreenUseCase.requestUser(login, object : RequestCallback<GithubUser> {
            override fun onSuccess(value: GithubUser) {
                _loadingLiveData.postValue(false)
                _userLiveData.postValue(value)
                loginScreenUseCase.addNewLocalUser(value)
            }

            override fun onError(t: Throwable) {
                _loadingLiveData.postValue(false)
                _errorLiveData.postValue(t.localizedMessage)
            }

        })
    }

    fun getUsersList() {
        _loadingLiveData.postValue(true)

        loginScreenUseCase.getLocalUsersList(object : RequestCallback<List<GithubUser>> {
            override fun onSuccess(value: List<GithubUser>) {
                _loadingLiveData.postValue(false)
                _usersListLiveData.postValue(value)
            }

            override fun onError(t: Throwable) {
                _loadingLiveData.postValue(false)
                _errorLiveData.postValue(t.localizedMessage)
            }

        })
    }

    fun deleteUserFromLocalStorage(githubUser: GithubUser) {
        loginScreenUseCase.deleteLocalUser(githubUser)
    }

    override fun onCleared() {
        loginScreenUseCase.clearDisposable()
        super.onCleared()
    }
}