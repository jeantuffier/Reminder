package fr.jeantuffier.reminder.free.home.injection

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.dao.AppDatabase
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.home.presentation.*
import java.lang.ref.WeakReference

@Module
class HomeActivityModule(private val activity : HomeActivity) {

    companion object {
        private const val DB_NAME = "reminder_database"
    }

    @Provides
    @ActivityScope
    fun providesTaskListActivity() = activity

    @Provides
    @ActivityScope
    fun providesTaskListPresenter() : HomeContract.Presenter {
        val appDatabase = Room.databaseBuilder(activity, AppDatabase::class.java, DB_NAME).build()
        return HomePresenter(activity, activity, appDatabase.taskDAO())
    }

}