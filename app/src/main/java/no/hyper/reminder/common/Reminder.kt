package no.hyper.reminder.common

import android.app.Application
import no.hyper.reminder.common.injection.component.ApplicationComponent
import no.hyper.reminder.common.injection.module.ApplicationModule

/**
 * Created by jean on 25.10.2016.
 */
class Reminder : Application() {

    lateinit var component : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        component = DaggerApplicationComponent.builder()
                .appModule(ApplicationModule(this))
                .build()
    }

}