package kosmicbor.githubclientapp.domain

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val eventsUrl: String,
    val type: String,
    val siteAdmin: Boolean,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val createdAt: String?,
    val updatedAt: String?
)