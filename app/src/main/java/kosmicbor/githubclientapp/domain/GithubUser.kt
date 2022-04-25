package kosmicbor.githubclientapp.domain

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val login: String,
    val avatarUrl: String
)