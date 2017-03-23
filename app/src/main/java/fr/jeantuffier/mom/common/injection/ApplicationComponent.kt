package fr.jeantuffier.mom.common.injection

import android.app.Application
import dagger.Component
import fr.jeantuffier.mom.common.injection.ApplicationModule
import fr.jeantuffier.mom.create.injection.CreateTaskActivityComponent
import fr.jeantuffier.mom.create.injection.CreateTaskActivityModule
import fr.jeantuffier.mom.display.injection.DisplayTaskActivityComponent
import fr.jeantuffier.mom.display.injection.DisplayTaskActivityModule
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