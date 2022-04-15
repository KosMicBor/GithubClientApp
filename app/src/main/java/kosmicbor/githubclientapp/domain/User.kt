package kosmicbor.githubclientapp.domain

data class User(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val reposList: List<UserRepo>
)