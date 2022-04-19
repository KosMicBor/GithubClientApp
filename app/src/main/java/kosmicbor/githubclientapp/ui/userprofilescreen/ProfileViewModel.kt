package kosmicbor.githubclientapp.ui.userprofilescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kosmicbor.githubclientapp.data.MockGithubRepositoryImpl
import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.User

class ProfileViewModel(private val repo: GitHubRepository) : ViewModel() {

    private val _profileLiveData = MutableLiveData<User>()
    val profileLiveData = _profileLiveData

    fun getUser(userId: Int) {
        repo.getUserRequest(userId, object : MockGithubRepositoryImpl.OnGetUserListener {
            override fun getUserRequestSuccess(value: User) {
                _profileLiveData.postValue(value)
            }

            override fun getUserRequestError(error: String) {
                throw Exception(error)
            }
        })
    }

}