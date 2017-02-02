package fr.jeantuffier.reminder.display.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.reminder.common.injection.ActivityScope
import fr.jeantuffier.reminder.display.model.DisplayTaskModel
import fr.jeantuffier.reminder.display.presenter.ProvidedDisplayTaskPresenterOps
import fr.jeantuffier.reminder.display.presenter.DisplayTaskPresenter
import fr.jeantuffier.reminder.display.view.DisplayTaskActivity
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