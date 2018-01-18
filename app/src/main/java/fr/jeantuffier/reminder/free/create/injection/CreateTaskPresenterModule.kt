package fr.jeantuffier.reminder.free.create.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskContract
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskPresenter

@Module
class CreateTaskPresenterModule {

    @Provides
    fun providesCreateTaskPresenter(context: Context, view: CreateTaskContract.View, taskDao: TaskDao) : CreateTaskContract.Presenter {
        return CreateTaskPresenter(context, view, taskDao)
    }
    
}