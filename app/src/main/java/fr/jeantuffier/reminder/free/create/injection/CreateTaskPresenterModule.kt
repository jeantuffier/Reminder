package fr.jeantuffier.reminder.free.create.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskContract
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskPresenter

@Module
class CreateTaskPresenterModule {

    @Provides
    fun providesCreateTaskPresenter(view: CreateTaskContract.View, taskDao: TaskDao) : CreateTaskContract.Presenter {
        return CreateTaskPresenter(view, taskDao)
    }
    
}