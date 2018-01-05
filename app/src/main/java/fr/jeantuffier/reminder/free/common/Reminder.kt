package fr.jeantuffier.reminder.free.common

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import fr.jeantuffier.reminder.free.common.injection.DaggerReminderComponent
import javax.inject.Inject

class Reminder : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerReminderComponent.create().inject(this)
    }

    override public fun activityInjector() = dispatchingAndroidInjector

}