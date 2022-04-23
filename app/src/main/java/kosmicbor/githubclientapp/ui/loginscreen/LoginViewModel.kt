package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kosmicbor.githubclientapp.domain.GithubUser
import kosmicbor.githubclientapp.domain.usecases.AddNewLocalUserUseCase
import kosmicbor.githubclientapp.domain.usecases.GetLocalUsersListUseCase
import kosmicbor.githubclientapp.domain.usecases.RequestUserFromServerUseCase
import kosmicbor.githubclientapp.utils.RequestCallback

class LoginViewModel(
    private val requestUserFromServerUseCase: RequestUserFromServerUseCase,
    private val addNewLocalUserUseCase: AddNewLocalUserUseCase,
    private val getLocalUsersListUseCase: GetLocalUsersListUseCase
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

        requestUserFromServerUseCase.requestUser(login, object : RequestCallback<GithubUser> {
            override fun onSuccess(value: GithubUser) {
                _loadingLiveData.postValue(false)
                _userLiveData.postValue(value)
                addNewLocalUserUseCase.addNewLocalUser(value)
            }

            override fun onError(t: Throwable) {
                _loadingLiveData.postValue(false)
                _errorLiveData.postValue(t.localizedMessage)
            }

        })
    }

    override fun onCleared() {
        requestUserFromServerUseCase.clearDisposable()
        super.onCleared()
    }

    fun getUsersList() {
        _loadingLiveData.postValue(true)

        getLocalUsersListUseCase.getLocalUsersList(object : RequestCallback<List<GithubUser>> {
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
}