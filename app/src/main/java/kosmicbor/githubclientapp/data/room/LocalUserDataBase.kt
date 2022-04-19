package kosmicbor.githubclientapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalUserEntity::class], version = 1, exportSchema = false)
abstract class LocalUserDataBase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
}