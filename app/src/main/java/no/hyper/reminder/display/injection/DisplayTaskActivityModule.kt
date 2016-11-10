package no.hyper.reminder.display.injection

import dagger.Module
import dagger.Provides
import no.hyper.reminder.common.injection.ActivityScope
import no.hyper.reminder.display.model.DisplayTaskModel
import no.hyper.reminder.display.presenter.ProvidedDisplayTaskPresenterOps
import no.hyper.reminder.display.presenter.DisplayTaskPresenter
import no.hyper.reminder.display.view.DisplayTaskActivity

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
        val presenter = DisplayTaskPresenter(activity)
        val model = DisplayTaskModel(presenter)
        presenter.model = model

        return presenter
    }

}