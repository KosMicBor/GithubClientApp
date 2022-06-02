package kosmicbor.githubclientapp.domain.usecases

interface LoginScreenUseCase : AddNewLocalUserUseCase, GetLocalUsersListUseCase,
    RequestUserFromServerUseCase, DeleteUserFromLocalStorageUseCase {
}