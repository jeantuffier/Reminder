package fr.jeantuffier.reminder.free.common.injection

import android.app.Application
import dagger.Component
import fr.jeantuffier.reminder.free.create.injection.CreateTaskActivityComponent
import fr.jeantuffier.reminder.free.create.injection.CreateTaskActivityModule
import fr.jeantuffier.reminder.free.home.injection.DisplayTaskActivityComponent
import fr.jeantuffier.reminder.free.home.injection.DisplayTaskActivityModule
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