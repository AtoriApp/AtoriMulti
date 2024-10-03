package app.atori.multi.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import app.atori.multi.datastore.AtoriDatabase
import java.io.File

actual object Multiplatform {
    actual fun getAtoriDbBuilder(): RoomDatabase.Builder<AtoriDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
        return Room.databaseBuilder<AtoriDatabase>(
            name = dbFile.absolutePath,
        )
    }
}