package fr.jeantuffier.reminder.free.common

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import fr.jeantuffier.reminder.free.common.injection.ApplicationComponent
import fr.jeantuffier.reminder.free.common.injection.ApplicationModule
import fr.jeantuffier.reminder.free.common.injection.DaggerApplicationComponent

/**
 * Created by jean on 25.10.2016.
 */
class Reminder private constructor() : Application() {

    companion object {
        val instance by lazy { Reminder() }
    }

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
        LeakCanary.install(this)
    }

    private fun initApplicationComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}