package no.hyper.reminder.common.injection.component

import dagger.Subcomponent
import no.hyper.reminder.common.injection.module.TaskListActivityModule
import no.hyper.reminder.common.injection.scope.ActivityScope
import no.hyper.reminder.list.view.TaskListActivity

/**
 * Created by jean on 25.10.2016.
 */

@ActivityScope
@Subcomponent( modules = kotlin.arrayOf(TaskListActivityModule::class))
interface TaskListActivityComponent {
    fun inject(activity: TaskListActivity)
}