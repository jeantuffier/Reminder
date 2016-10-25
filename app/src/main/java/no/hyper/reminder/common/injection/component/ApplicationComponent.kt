package no.hyper.reminder.common.injection.component

import android.app.Application
import dagger.Component
import no.hyper.reminder.common.injection.module.ApplicationModule
import no.hyper.reminder.common.injection.module.TaskListActivityModule
import javax.inject.Singleton

/**
 * Created by jean on 25.10.2016.
 */

@Singleton
@Component( modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun application() : Application
    fun getTaskListComponent(module: TaskListActivityModule) : TaskListActivityComponent

}