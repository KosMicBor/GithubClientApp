package kosmicbor.githubclientapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class GithubUserRepoEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("url")
    val url: String?
)