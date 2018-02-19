package fr.jeantuffier.reminder.free.create.injection

import dagger.Binds
import dagger.Module
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskContract

@Module
abstract class CreateTaskViewModule {

    @Binds
    abstract fun providesCreateTaskView(activity: CreateTaskActivity): CreateTaskContract.View

}
