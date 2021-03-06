package kosmicbor.githubclientapp.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface LocalUserDao {
    @Query("SELECT * FROM LocalUserDto")
    fun getAllUsers(): List<LocalUserDto>

    @Query("SELECT * FROM LocalUserDto WHERE login LIKE :login")
    fun getLocalUser(login: String): LocalUserDto

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewLocalUser(user: LocalUserDto)

    @Update
    fun updateCurrentLocalUser(user: LocalUserDto)

    @Delete
    fun deleteCurrentLocalUser(user: LocalUserDto): Single<Int>
}