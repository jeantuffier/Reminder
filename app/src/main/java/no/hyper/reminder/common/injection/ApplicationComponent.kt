package no.hyper.reminder.common.injection

import android.app.Application
import dagger.Component
import no.hyper.reminder.common.injection.ApplicationModule
import no.hyper.reminder.create.injection.CreateTaskActivityComponent
import no.hyper.reminder.create.injection.CreateTaskActivityModule
import no.hyper.reminder.display.injection.DisplayTaskActivityComponent
import no.hyper.reminder.display.injection.DisplayTaskActivityModule
import javax.inject.Singleton

/**
 * Created by jean on 25.10.2016.
 */

@Singleton
@Component( modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun application() : Application
    fun getTaskListComponent(module: DisplayTaskActivityModule) : DisplayTaskActivityComponent
    fun getCreateTaskComponent(module: CreateTaskActivityModule) : CreateTaskActivityComponent

}