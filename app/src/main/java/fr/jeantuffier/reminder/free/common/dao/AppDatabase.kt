package fr.jeantuffier.reminder.free.common.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import fr.jeantuffier.reminder.free.common.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDAO(): TaskDao

}
