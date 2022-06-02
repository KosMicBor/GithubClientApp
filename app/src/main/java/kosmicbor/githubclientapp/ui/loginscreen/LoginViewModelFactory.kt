package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kosmicbor.githubclientapp.di.qualifiers.LoginViewModelFactoryQualifier
import kosmicbor.githubclientapp.di.scopes.GithubApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@LoginViewModelFactoryQualifier
@GithubApplicationScope
class LoginViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }

}


