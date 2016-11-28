package fr.jeantuffier.reminder.create.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.common.injection.ActivityScope
import fr.jeantuffier.reminder.create.view.activity.CreateTaskActivity

/**
 * Created by jean on 01.11.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(CreateTaskActivityModule::class))
interface CreateTaskActivityComponent {
    fun inject(activity: CreateTaskActivity)
}