package fr.jeantuffier.reminder.free.create.injection

import dagger.Subcomponent
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity

@ActivityScope
@Subcomponent(modules = [CreateTaskActivityModule::class])
interface CreateTaskActivityComponent {
    fun inject(activity: CreateTaskActivity)
}