package no.hyper.reminder.common.model.task.regular

import no.hyper.reminder.common.model.task.Task
import no.hyper.reminder.common.model.task.ViewTypeFactory
import no.hyper.reminder.common.model.timer.Timer

/**
 * Created by jean on 14.10.2016.
 */

data class RegularTask(
        private val title: String,
        private val priority: Task.Priority,
        private val timer: Timer
) : Task {

    override fun getViewType(factory: ViewTypeFactory) = factory.type(this)

    override fun getTitle() = title

    override fun getPriority() = priority

    override fun getTimer() = timer

}