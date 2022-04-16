package kosmicbor.githubclientapp.data

import kosmicbor.githubclientapp.domain.GitHubRepository
import kosmicbor.githubclientapp.domain.User
import kosmicbor.githubclientapp.domain.UserRepo

class MockGithubRepositoryImpl : GitHubRepository {

    private val usersList: MutableList<User> = mutableListOf()

    override fun getUsersListRequest(): List<User> {

        usersList.add(
            User(
                0,
                "User1",
                "https://avatars.githubusercontent.com/u/70434750?s=400&v=4",
                listOf(
                    UserRepo(0, 0, "Login", false),
                    UserRepo(1, 0, "TestRepo", true),
                    UserRepo(2, 0, "BeginRepo", false),
                    UserRepo(3, 0, "EndRepo", false)
                )
            )
        )

        usersList.add(
            User(
                1,
                "User2",
                "https://avatars.githubusercontent.com/u/70434750?s=400&v=4",
                listOf(
                    UserRepo(0, 1, "Login", false),
                    UserRepo(2, 1, "BeginRepo", false),
                    UserRepo(3, 1, "EndRepo", false)
                )
            )
        )

        usersList.add(
            User(
                2,
                "User3",
                "https://avatars.githubusercontent.com/u/70434750?s=400&v=4",
                listOf(
                    UserRepo(1, 2, "TestRepo", true),
                    UserRepo(2, 2, "BeginRepo", false),
                    UserRepo(3, 2, "EndRepo", false)
                )
            )
        )

        usersList.add(
            User(
                3,
                "User4",
                "https://avatars.githubusercontent.com/u/70434750?s=400&v=4",
                listOf(
                    UserRepo(0, 0, "Login", false),
                    UserRepo(1, 0, "TestRepo", true),
                )
            )
        )

        return usersList
    }

    override fun getUserRequest(id: Int, onGetUserListener: OnGetUserListener) {
        usersList.forEach { user ->
            if (user.id == id) {
                onGetUserListener.getUserRequestSuccess(user)
                return
            }
        }

        onGetUserListener.getUserRequestError("User not found")
    }

    interface OnGetUserListener {
        fun getUserRequestSuccess(value: User)
        fun getUserRequestError(error: String)
    }
}