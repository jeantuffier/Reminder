package no.hyper.reminder.common

import android.app.Application
import android.content.Context
import no.hyper.reminder.common.injection.component.ApplicationComponent
import no.hyper.reminder.common.injection.component.DaggerApplicationComponent as appComponent
import no.hyper.reminder.common.injection.module.ApplicationModule

/**
 * Created by jean on 25.10.2016.
 */
class Reminder : Application() {

    companion object {
        fun get(context: Context) = context.applicationContext as Reminder
    }

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        component = appComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}