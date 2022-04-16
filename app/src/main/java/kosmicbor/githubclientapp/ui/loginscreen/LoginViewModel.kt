package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.User

class LoginViewModel(private val userRepo: GitHubRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>> = _userLiveData

    fun getUsersList() {
        _userLiveData.postValue(userRepo.getUsersListRequest())
    }
}