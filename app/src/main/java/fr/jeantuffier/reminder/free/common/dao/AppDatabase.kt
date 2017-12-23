package fr.jeantuffier.reminder.free.common.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import fr.jeantuffier.reminder.free.common.Reminder
import fr.jeantuffier.reminder.free.common.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "reminder_database"
    }

    abstract fun taskDAO(): TaskDao

    val appDatabase = Room.databaseBuilder(Reminder.instance.applicationContext, AppDatabase::class.java, DB_NAME)
            .build()
}