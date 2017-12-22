package fr.jeantuffier.reminder.free.display.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.free.common.injection.ActivityScope
import fr.jeantuffier.reminder.free.display.model.DisplayTaskModel
import fr.jeantuffier.reminder.free.display.presenter.ProvidedDisplayTaskPresenterOps
import fr.jeantuffier.reminder.free.display.presenter.DisplayTaskPresenter
import fr.jeantuffier.reminder.free.display.view.DisplayTaskActivity
import java.lang.ref.WeakReference

/**
 * Created by jean on 25.10.2016.
 */
@Module
class DisplayTaskActivityModule(private val activity : DisplayTaskActivity) {

    @Provides
    @ActivityScope
    fun providesTaskListActivity() = activity

    @Provides
    @ActivityScope
    fun providesTaskListPresenter() : ProvidedDisplayTaskPresenterOps {
        val presenter = DisplayTaskPresenter(WeakReference(activity))
        val model = DisplayTaskModel(presenter)
        presenter.model = model

        return presenter
    }

}