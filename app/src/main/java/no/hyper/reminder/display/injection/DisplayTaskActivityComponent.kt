package no.hyper.reminder.display.injection

import dagger.Subcomponent
import no.hyper.reminder.display.injection.DisplayTaskActivityModule
import no.hyper.reminder.common.injection.ActivityScope
import no.hyper.reminder.display.view.DisplayTaskActivity

/**
 * Created by jean on 25.10.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(DisplayTaskActivityModule::class))
interface DisplayTaskActivityComponent {
    fun inject(activity: DisplayTaskActivity)
}