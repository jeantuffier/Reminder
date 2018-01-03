package fr.jeantuffier.reminder.free.create.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskContract
import fr.jeantuffier.reminder.free.create.presentation.CreateTaskPresenter

@Module
class CreateTaskPresenterModule {

    @Provides
    fun providesCreateTaskPresenter(view: CreateTaskContract.View) : CreateTaskContract.Presenter {
        return CreateTaskPresenter(view)
    }
    
}