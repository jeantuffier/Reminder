package no.hyper.reminder.create.injection

import dagger.Module
import dagger.Provides
import no.hyper.reminder.common.injection.ActivityScope
import no.hyper.reminder.create.presenter.CreateTaskPresenter
import no.hyper.reminder.create.presenter.ProvidedCreateTaskListPresenterOps
import no.hyper.reminder.create.view.activity.CreateTaskActivity

/**
 * Created by jean on 01.11.2016.
 */
@Module
class CreateTaskActivityModule(private val activity : CreateTaskActivity) {

    @Provides
    @ActivityScope
    fun providesCreateTaskActivity() = activity

    @Provides
    @ActivityScope
    fun providesCreateTaskPresenter() : ProvidedCreateTaskListPresenterOps {
        val presenter = CreateTaskPresenter(activity)
        //val model = TaskListModel(presenter)
        //presenter.model = model

        return presenter
    }

}