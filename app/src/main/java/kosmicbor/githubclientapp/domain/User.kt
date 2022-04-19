package kosmicbor.githubclientapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val reposList: List<UserRepo>
) : Parcelable