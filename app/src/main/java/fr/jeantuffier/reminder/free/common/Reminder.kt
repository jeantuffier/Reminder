package fr.jeantuffier.reminder.free.common

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import fr.jeantuffier.reminder.free.common.injection.DaggerReminderComponent
import fr.jeantuffier.reminder.free.common.injection.ReminderModule
import javax.inject.Inject

const val DB_NAME = "reminder_database"

class Reminder : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerReminderComponent.builder()
            .reminderModule(ReminderModule(this))
            .build()
            .inject(this)
    }

    override public fun activityInjector() = dispatchingAndroidInjector

}