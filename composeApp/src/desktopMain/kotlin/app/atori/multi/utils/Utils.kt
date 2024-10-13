package app.atori.multi.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import app.atori.multi.databases.AtoriDatabase
import java.io.File

actual object MultiplatformIO {
    actual fun getAtoriDbBuilder(): RoomDatabase.Builder<AtoriDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), DatabaseUtils.DATABASE_NAME)
        return Room.databaseBuilder<AtoriDatabase>(
            name = dbFile.absolutePath,
        )
    }
}