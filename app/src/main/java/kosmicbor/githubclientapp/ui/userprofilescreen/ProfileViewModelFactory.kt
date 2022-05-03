package kosmicbor.githubclientapp.ui.userprofilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kosmicbor.githubclientapp.domain.usecases.ProfileScreenUseCase

class ProfileViewModelFactory(
    private val profileScreenUseCase: ProfileScreenUseCase,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(
            profileScreenUseCase
        ) as T
    }
}