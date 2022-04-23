package kosmicbor.githubclientapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUserEntity(
    @PrimaryKey val login: String,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "node_id") val nodeId: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String,
    @ColumnInfo(name = "followers_url") val followersUrl: String,
    @ColumnInfo(name = "following_url") val followingUrl: String,
    @ColumnInfo(name = "subscriptions_url") val subscriptionsUrl: String,
    @ColumnInfo(name = "organizations_url") val organizationsUrl: String,
    @ColumnInfo(name = "repos_url") val reposUrl: String,
    @ColumnInfo(name = "events_url") val eventsUrl: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "site_admin") val siteAdmin: Boolean,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "blog") val blog: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "bio") val bio: String?,
    @ColumnInfo(name = "created_at") val createdAt: String?,
    @ColumnInfo(name = "updated_at") val updatedAt: String?
)
