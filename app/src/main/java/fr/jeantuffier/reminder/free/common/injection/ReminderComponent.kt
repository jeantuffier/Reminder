package fr.jeantuffier.reminder.free.common.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import fr.jeantuffier.reminder.free.common.Reminder
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivitiesModule::class, ReminderModule::class])
interface ReminderComponent : AndroidInjector<Reminder> {

    fun getTaskDao(): TaskDao

}