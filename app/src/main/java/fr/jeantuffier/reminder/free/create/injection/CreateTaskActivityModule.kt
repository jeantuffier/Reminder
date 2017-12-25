package fr.jeantuffier.reminder.free.create.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskActivity
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskPresenter

@Module
class CreateTaskActivityModule(private val activity: CreateTaskActivity) {

    @Provides
    @ActivityScope
    fun providesCreateTaskActivity() = activity

    @Provides
    @ActivityScope
    fun providesCreateTaskPresenter() = CreateTaskPresenter(activity, activity)
}
