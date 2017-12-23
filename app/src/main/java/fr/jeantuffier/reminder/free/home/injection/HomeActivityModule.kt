package fr.jeantuffier.reminder.free.home.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.home.presentation.*
import java.lang.ref.WeakReference

@Module
class HomeActivityModule(private val activity : HomeActivity) {

    @Provides
    @ActivityScope
    fun providesTaskListActivity() = activity

    @Provides
    @ActivityScope
    fun providesTaskListPresenter() : HomeContract.Presenter {
        val presenter = HomePresenter(activity, WeakReference(activity))
        val model = HomeModel(presenter)
        presenter.model = model

        return presenter
    }

}