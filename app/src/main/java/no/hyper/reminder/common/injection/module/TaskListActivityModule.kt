package no.hyper.reminder.common.injection.module

import dagger.Module
import dagger.Provides
import no.hyper.reminder.common.injection.scope.ActivityScope
import no.hyper.reminder.list.model.TaskListModel
import no.hyper.reminder.list.presenter.ProvidedTaskListPresenterOps
import no.hyper.reminder.list.presenter.TaskListPresenter
import no.hyper.reminder.list.view.TaskListActivity

/**
 * Created by jean on 25.10.2016.
 */

@Module
class TaskListActivityModule(private val activity : TaskListActivity) {

    @Provides
    @ActivityScope
    fun providesTaskListActivity() = activity

    @Provides
    @ActivityScope
    fun providesTaskListPresenter() : ProvidedTaskListPresenterOps {
        val presenter = TaskListPresenter(activity)
        val model = TaskListModel(presenter)
        presenter.model = model

        return presenter
    }

}