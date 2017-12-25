package fr.jeantuffier.reminder.free.common.injection

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.dao.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule(val application: Application) {

    companion object {
        private const val DB_NAME = "reminder_database"
    }

    private val appDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase() = appDatabase

    @Singleton
    @Provides
    internal fun providesTaskDao(appDatabase: AppDatabase) = appDatabase.taskDAO()

}