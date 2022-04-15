package kosmicbor.githubclientapp.domain

data class UserRepo(
    val id: Int,
    val nodeId: Int,
    val name: String,
    val isPrivate: Boolean
)