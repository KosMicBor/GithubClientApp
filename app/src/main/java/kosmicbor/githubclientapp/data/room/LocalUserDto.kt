package kosmicbor.githubclientapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUserDto(
    @PrimaryKey val login: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String
)
