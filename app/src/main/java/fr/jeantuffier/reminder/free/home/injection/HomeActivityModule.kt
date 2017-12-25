package fr.jeantuffier.reminder.free.home.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.home.presentation.HomeActivity
import fr.jeantuffier.reminder.free.home.presentation.HomePresenter

@Module
class HomeActivityModule(private val activity: HomeActivity) {

    @Provides
    @ActivityScope
    fun providesTaskListActivity() = activity

    @Provides
    @ActivityScope
    fun providesTaskListPresenter() = HomePresenter(activity, activity)
}
