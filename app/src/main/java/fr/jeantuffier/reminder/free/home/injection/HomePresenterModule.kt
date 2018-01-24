package fr.jeantuffier.reminder.free.home.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.dao.TaskDao
import fr.jeantuffier.reminder.free.home.presentation.HomeContract
import fr.jeantuffier.reminder.free.home.presentation.HomePresenter

@Module
class HomePresenterModule {

    @Provides
    fun provideHomePresenter(context: Context, view: HomeContract.View, taskDao: TaskDao): HomeContract.Presenter {
        return HomePresenter(context, view, taskDao)
    }

}