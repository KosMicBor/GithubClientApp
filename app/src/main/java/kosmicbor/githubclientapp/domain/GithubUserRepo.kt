package kosmicbor.githubclientapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepo(
    val id: Int,
    val nodeId: String,
    val name: String?,
    val description: String?,
    val isPrivate: Boolean,
    val url: String?
) : Parcelable