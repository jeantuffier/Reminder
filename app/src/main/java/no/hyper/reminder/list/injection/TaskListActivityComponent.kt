package no.hyper.reminder.list.injection

import dagger.Subcomponent
import no.hyper.reminder.list.injection.TaskListActivityModule
import no.hyper.reminder.common.injection.ActivityScope
import no.hyper.reminder.list.view.TaskListActivity

/**
 * Created by jean on 25.10.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(TaskListActivityModule::class))
interface TaskListActivityComponent {
    fun inject(activity: TaskListActivity)
}