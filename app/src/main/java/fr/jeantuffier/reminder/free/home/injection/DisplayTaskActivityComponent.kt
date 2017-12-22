package fr.jeantuffier.reminder.free.home.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity

/**
 * Created by jean on 25.10.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(DisplayTaskActivityModule::class))
interface DisplayTaskActivityComponent {
    fun inject(activity: HomeActivity)
}