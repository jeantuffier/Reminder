package fr.jeantuffier.reminder.common.injection

import android.app.Application
import dagger.Component
import fr.jeantuffier.reminder.common.injection.ApplicationModule
import fr.jeantuffier.reminder.create.injection.CreateTaskActivityComponent
import fr.jeantuffier.reminder.create.injection.CreateTaskActivityModule
import fr.jeantuffier.reminder.display.injection.DisplayTaskActivityComponent
import fr.jeantuffier.reminder.display.injection.DisplayTaskActivityModule
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