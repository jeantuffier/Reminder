package fr.jeantuffier.reminder.free.home.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.home.presentation.HomeContract
import fr.jeantuffier.reminder.free.home.presentation.HomePresenter

@Module
class HomePresenterModule {

    @Provides
    fun provideHomePresenter(view: HomeContract.View): HomeContract.Presenter {
        return HomePresenter(view)
    }

}