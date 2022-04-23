package kosmicbor.githubclientapp.data.room

import androidx.room.*

@Dao
interface LocalUserDao {
    @Query("SELECT * FROM LocalUserEntity")
    fun getAllUsers(): List<LocalUserEntity>

    @Query("SELECT * FROM LocalUserEntity WHERE login LIKE :login")
    fun getLocalUser(login: String): LocalUserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewLocalUser(user: LocalUserEntity)

    @Update
    fun updateCurrentLocalUser(user: LocalUserEntity)

    @Delete
    fun deleteCurrentLocalUser(user: LocalUserEntity)
}