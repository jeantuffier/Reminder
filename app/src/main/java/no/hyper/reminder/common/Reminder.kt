package no.hyper.reminder.common

import android.app.Application
import android.content.Context
import no.hyper.reminder.common.injection.ApplicationComponent
import no.hyper.reminder.common.injection.ApplicationModule
import no.hyper.reminder.common.injection.DaggerApplicationComponent

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
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}