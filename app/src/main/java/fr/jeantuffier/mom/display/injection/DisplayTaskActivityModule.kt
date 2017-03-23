package fr.jeantuffier.mom.display.injection

import dagger.Module
import dagger.Provides
import fr.jeantuffier.mom.common.injection.ActivityScope
import fr.jeantuffier.mom.display.model.DisplayTaskModel
import fr.jeantuffier.mom.display.presenter.ProvidedDisplayTaskPresenterOps
import fr.jeantuffier.mom.display.presenter.DisplayTaskPresenter
import fr.jeantuffier.mom.display.view.DisplayTaskActivity
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