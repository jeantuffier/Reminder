package fr.jeantuffier.mom.display.injection

import dagger.Subcomponent
import fr.jeantuffier.mom.display.injection.DisplayTaskActivityModule
import fr.jeantuffier.mom.common.injection.ActivityScope
import fr.jeantuffier.mom.display.view.DisplayTaskActivity

/**
 * Created by jean on 25.10.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(DisplayTaskActivityModule::class))
interface DisplayTaskActivityComponent {
    fun inject(activity: DisplayTaskActivity)
}