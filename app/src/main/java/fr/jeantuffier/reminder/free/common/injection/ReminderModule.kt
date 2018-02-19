package fr.jeantuffier.reminder.free.common.injection

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.DB_NAME
import fr.jeantuffier.reminder.free.common.dao.AppDatabase
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import javax.inject.Singleton

@Module
class ReminderModule(private val application: Application) {

    private val database by lazy {
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesContext(): Context = application

    @Singleton
    @Provides
    fun providesAppDatabase(): AppDatabase = database

    @Singleton
    @Provides
    fun providesTaskDao(): TaskDao = database.taskDAO()

}