package fr.jeantuffier.reminder.free.common.injection

import android.app.Application
import dagger.Component
import fr.jeantuffier.reminder.free.common.dao.AppDatabase
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.create.injection.CreateTaskActivityComponent
import fr.jeantuffier.reminder.free.create.injection.CreateTaskActivityModule
import fr.jeantuffier.reminder.free.home.injection.HomeActivityComponent
import fr.jeantuffier.reminder.free.home.injection.HomeActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RoomModule::class])
interface ApplicationComponent {

    fun appDatabase(): AppDatabase
    fun taskDao(): TaskDao

    fun application(): Application
    fun getTaskListComponent(module: HomeActivityModule): HomeActivityComponent
    fun getCreateTaskComponent(module: CreateTaskActivityModule): CreateTaskActivityComponent

}