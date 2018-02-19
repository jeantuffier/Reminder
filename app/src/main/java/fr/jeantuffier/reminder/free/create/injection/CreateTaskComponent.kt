package fr.jeantuffier.reminder.free.create.injection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity

@Subcomponent(modules = [CreateTaskViewModule::class, CreateTaskPresenterModule::class])
interface CreateTaskComponent : AndroidInjector<CreateTaskActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CreateTaskActivity>()
}