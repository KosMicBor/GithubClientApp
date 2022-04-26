package kosmicbor.githubclientapp.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kosmicbor.githubclientapp.domain.usecases.AddNewLocalUserUseCase
import kosmicbor.githubclientapp.domain.usecases.DeleteUserFromLocalStorageUseCase
import kosmicbor.githubclientapp.domain.usecases.GetLocalUsersListUseCase
import kosmicbor.githubclientapp.domain.usecases.RequestUserFromServerUseCase

class LoginViewModelFactory(
    private val requestUserFromServerUseCase: RequestUserFromServerUseCase,
    private val addNewLocalUserUseCase: AddNewLocalUserUseCase,
    private val getLocalUsersListUseCase: GetLocalUsersListUseCase,
    private val deleteUserFromLocalStorageUseCase: DeleteUserFromLocalStorageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(
            requestUserFromServerUseCase,
            addNewLocalUserUseCase,
            getLocalUsersListUseCase,
            deleteUserFromLocalStorageUseCase
        ) as T
    }
}