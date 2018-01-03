package fr.jeantuffier.reminder.free.home.injection

import dagger.Binds
import dagger.Module
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity
import fr.jeantuffier.reminder.free.home.presentation.HomeContract

@Module
abstract class HomeViewModule {

    @Binds
    abstract fun providesHomeView(homeActivity: HomeActivity): HomeContract.View

}
