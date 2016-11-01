package no.hyper.reminder.create.injection

import dagger.Subcomponent
import no.hyper.reminder.common.injection.ActivityScope
import no.hyper.reminder.create.view.activity.CreateTaskActivity

/**
 * Created by jean on 01.11.2016.
 */
@ActivityScope
@Subcomponent( modules = arrayOf(CreateTaskActivityModule::class))
interface CreateTaskActivityComponent {
    fun inject(activity: CreateTaskActivity)
}