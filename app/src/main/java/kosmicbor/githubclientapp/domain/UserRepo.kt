package kosmicbor.githubclientapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepo(
    val id: Int,
    val nodeId: Int,
    val name: String,
    val isPrivate: Boolean
) : Parcelable