package fr.jeantuffier.reminder.free.common

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import fr.jeantuffier.reminder.free.common.injection.ApplicationComponent
import fr.jeantuffier.reminder.free.common.injection.ApplicationModule
import fr.jeantuffier.reminder.free.common.injection.DaggerApplicationComponent

class Reminder : Application() {

    companion object {
        val instance by lazy { Reminder() }
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }

}