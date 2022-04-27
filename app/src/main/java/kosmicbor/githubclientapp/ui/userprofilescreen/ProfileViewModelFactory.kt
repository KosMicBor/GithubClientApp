package kosmicbor.githubclientapp.ui.userprofilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kosmicbor.githubclientapp.domain.usecases.RequestUserFromServerUseCase
import kosmicbor.githubclientapp.domain.usecases.RequestUserReposFromServerUseCase

class ProfileViewModelFactory(
    private val requestUserFromServerUseCase: RequestUserFromServerUseCase,
    private val requestUserReposFromServerUseCase: RequestUserReposFromServerUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            requestUserFromServerUseCase,
            requestUserReposFromServerUseCase
        ) as T
    }
}