package kosmicbor.githubclientapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepoEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("url")
    val url: String?
) : Parcelable