package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kosmicbor.githubclientapp.domain.usecases.LoginScreenUseCase
import kosmicbor.githubclientapp.utils.ResourcesHelper

class LoginViewModelFactory(

    private val loginScreenUseCase: LoginScreenUseCase,
    private val resourcesHelper: ResourcesHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            loginScreenUseCase,
            resourcesHelper
        ) as T
    }
}
