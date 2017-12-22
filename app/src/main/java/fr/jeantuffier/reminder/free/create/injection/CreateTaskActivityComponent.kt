package fr.jeantuffier.reminder.free.create.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.create.view.activity.CreateTaskActivity

/**
 * Created by jean on 01.11.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(CreateTaskActivityModule::class))
interface CreateTaskActivityComponent {
    fun inject(activity: CreateTaskActivity)
}