package app.atori.multi.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import app.atori.multi.Universal.appContext
import app.atori.multi.databases.AtoriDatabase


actual object Multiplatform {
    actual fun getAtoriDbBuilder(): RoomDatabase.Builder<AtoriDatabase> {
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<AtoriDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}