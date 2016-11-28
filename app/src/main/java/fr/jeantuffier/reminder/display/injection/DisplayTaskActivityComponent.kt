package fr.jeantuffier.reminder.display.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.display.injection.DisplayTaskActivityModule
import fr.jeantuffier.reminder.common.injection.ActivityScope
import fr.jeantuffier.reminder.display.view.DisplayTaskActivity

/**
 * Created by jean on 25.10.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(DisplayTaskActivityModule::class))
interface DisplayTaskActivityComponent {
    fun inject(activity: DisplayTaskActivity)
}