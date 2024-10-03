package app.atori.multi.databases

import androidx.room.*
import app.atori.multi.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

@Database(entities = [MessageEntity::class], version = 1)
abstract class AtoriDatabase : RoomDatabase() {
    abstract fun getMsgDao(): MessageDao
}

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Insert
    suspend fun insertMessage(message: MessageEntity)
}

